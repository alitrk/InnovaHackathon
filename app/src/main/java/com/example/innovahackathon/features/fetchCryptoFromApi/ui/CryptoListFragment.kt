package com.example.innovahackathon.features.fetchCryptoFromApi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innovahackathon.R
import com.example.innovahackathon.databinding.FragmentCryptoListBinding
import com.example.innovahackathon.databinding.TitleBarBinding
import com.example.innovahackathon.features.fetchCryptoFromApi.data.model.CryptoItem
import com.example.innovahackathon.utils.Resource
import com.example.innovahackathon.utils.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CryptoListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var viewModel: CryptoListViewModel
    private lateinit var binding: FragmentCryptoListBinding
    private var cryptoSet = mutableSetOf<CryptoItem>()
    private lateinit var titleBarBinding: TitleBarBinding
    private lateinit var searchView: SearchView
    private lateinit var adapter: CryptoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crypto_list, container, false)
        binding.cryptoListFragment = this
        binding.rvCryptoList.layoutManager = LinearLayoutManager(requireContext())
        titleBarBinding = binding.include
        searchView = titleBarBinding.searchView
        searchView.setOnQueryTextListener(this@CryptoListFragment)
        observeCryptoList()
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CryptoListViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun observeCryptoList() {
        lifecycleScope.launch {
            viewModel.cryptoList.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Show loading indicator
                        binding.progressBar.isVisible = true
                    }

                    is Resource.Success -> {
                        adapter = CryptoListAdapter(requireContext(), resource.data!!)
                        binding.apply {
                            rvCryptoList.adapter = adapter
                            progressBar.isVisible = false
                            cryptoSet.addAll(resource.data)
                        }
                    }

                    is Resource.Error -> {
                        // Show error message
                        binding.apply {
                            tvErrorMessage.text = resource.message
                            progressBar.isVisible = false
                            tvErrorMessage.isVisible = true
                        }
                    }
                }
            }
        }
    }

    fun fabOnClick(view: View) {
        Navigation.navigate(view, R.id.action_cryptoListFragment_to_accountFragment)
    }

    fun cartOnClick(view: View) {
        Navigation.navigate(view, R.id.action_cryptoListFragment_to_mockBalanceFragment)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        filterList(newText)
        return true
    }

    private fun filterList(newText: String) {
        val filteredList = arrayListOf<CryptoItem>()
        for (i in cryptoSet) {
            if (i.displaySymbol.lowercase().contains(newText.lowercase())) {
                filteredList.add(i)
            }
        }
        if (filteredList.isNotEmpty()) {
            adapter.setFilteredList(filteredList)
        } else {
            adapter.setFilteredList(filteredList)
        }
    }

}
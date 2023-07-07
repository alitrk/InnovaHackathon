package com.example.innovahackathon.features.fetchCryptoFromApi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innovahackathon.R
import com.example.innovahackathon.databinding.FragmentCryptoListBinding
import com.example.innovahackathon.utils.Resource
import com.example.innovahackathon.utils.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CryptoListFragment : Fragment() {

    private lateinit var viewModel: CryptoListViewModel
    private lateinit var binding: FragmentCryptoListBinding

    private lateinit var adapter: CryptoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crypto_list, container, false)
        binding.cryptoListFragment = this
        binding.rvCryptoList.layoutManager = LinearLayoutManager(requireContext())
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
                        }

                    }

                    is Resource.Error -> {
                        // Show error message
                        binding.apply {
                            tvErrorMessage.text = resource.message
                            progressBar.isVisible = false
                            /*errorMessage.isVisible = true
                            retryBtn.isVisible = true*/
                        }
                        //titleBarBinding.buttonSurpriseMe.isClickable = false

                    }
                }
            }
        }
    }

    fun fabOnClick(view: View) {
        Navigation.navigate(view, R.id.action_cryptoListFragment_to_accountFragment)
    }

}
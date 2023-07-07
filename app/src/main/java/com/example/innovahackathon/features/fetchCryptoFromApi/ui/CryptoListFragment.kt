package com.example.innovahackathon.features.fetchCryptoFromApi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innovahackathon.databinding.FragmentCryptoListBinding
import com.example.innovahackathon.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CryptoListFragment : Fragment() {

    private lateinit var viewModel: CryptoListViewModel
    private var _binding: FragmentCryptoListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CryptoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoListBinding.inflate(inflater, container, false)
        binding.cryptoListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
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
                            cryptoListRecyclerview.adapter = adapter
                            progressBar.isVisible = false
                        }

                    }

                    is Resource.Error -> {
                        // Show error message
                        binding.apply {
                            errorMessage.text = resource.message
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

}
package com.example.innovahackathon.features.fetchCryptoPriceFromApi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs

import com.example.innovahackathon.databinding.FragmentCryptoPriceBinding
import com.example.innovahackathon.features.fetchCryptoFromApi.ui.CryptoListAdapter
import com.example.innovahackathon.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CryptoPriceFragment : Fragment() {

    private val args: CryptoPriceFragmentArgs by navArgs()
    private val viewModel: CryptoPriceViewModel by viewModels()

    private var _binding: FragmentCryptoPriceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoPriceBinding.inflate(inflater, container, false)

        val cryptoSymbol = args.symbol
        viewModel.fetchCryptoPrice(cryptoSymbol)
        observeCryptoPrice()

        return binding.root
    }

    private fun observeCryptoPrice() {
        viewModel.cryptoPrice.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    val cryptoPrice = resource.data
                    binding.apply {
                        if (cryptoPrice != null) {
                            tryTxt.text=  cryptoPrice.t.toString()
                        }
                    }

                }
                is Resource.Error -> {
                    val errorMessage = resource.message ?: "Error"
                    // Handle error response
                }
                is Resource.Loading -> {
                    // Handle loading state
                }
            }
        }
    }


}
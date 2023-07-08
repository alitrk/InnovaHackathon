package com.example.innovahackathon.features.getBalanceFromMock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.innovahackathon.databinding.FragmentMockBalanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MockBalanceFragment : Fragment() {
    private val viewModel: MockBalanceViewModel by viewModels()
    private var _binding: FragmentMockBalanceBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMockBalanceBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        observeMockBalance()
        return binding.root
    }


    private fun observeMockBalance() {
        viewModel.balanceItems.observe(viewLifecycleOwner) { balanceItems ->
            binding.apply {
                tvMockBalance.text = balanceItems.toString()
            }
        }
    }
}
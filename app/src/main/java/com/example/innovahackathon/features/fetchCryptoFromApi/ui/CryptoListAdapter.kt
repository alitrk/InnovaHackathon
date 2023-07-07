package com.example.innovahackathon.features.fetchCryptoFromApi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.innovahackathon.databinding.CryptoListItemBinding
import com.example.innovahackathon.features.fetchCryptoFromApi.data.model.CryptoItem


class CryptoListAdapter(private val context: Context, private val cryptoItems: List<CryptoItem>) :
    RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CryptoListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cryptoItem = cryptoItems[position]
        holder.bind(cryptoItem)
    }

    override fun getItemCount(): Int {
        return cryptoItems.size
    }

    inner class ViewHolder(private val binding: CryptoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cryptoItem: CryptoItem) {
            binding.apply {

                cryptoName.text = cryptoItem.displaySymbol

                root.setOnClickListener {
                    val directions = CryptoListFragmentDirections.actionCryptoListFragmentToCryptoPriceFragment(cryptoItem.symbol)
                    it.findNavController().navigate(directions)
                }
            }
        }
    }
}
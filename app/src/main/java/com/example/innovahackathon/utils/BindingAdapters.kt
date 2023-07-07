package com.example.innovahackathon.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar

object BindingAdapters {

    @BindingAdapter("hideOnLoading")
    fun ViewGroup.hideOnLoading(resource: Resource<Any>) {
        visibility = if (resource is Resource.Loading)
            View.GONE
        else
            View.VISIBLE
    }

    @BindingAdapter("showOnLoading")
    fun ProgressBar.showOnLoading(resource: Resource<Any>) {
        visibility = if (resource is Resource.Loading)
            View.VISIBLE
        else
            View.GONE
    }

    @BindingAdapter("showSnackbar")
    fun View.showSnackbar(message: String?) {
        message?.let {
            Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}
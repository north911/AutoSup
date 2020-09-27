package com.example.autosup.listeners

import android.widget.SearchView
import com.example.autosup.utils.RecyclerUpdater

class SearchViewListener(private val recyclerUpdater: RecyclerUpdater) : SearchView.OnQueryTextListener {


    override fun onQueryTextSubmit(query: String?): Boolean {
        println("123")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { recyclerUpdater.updateRecycler(newText)}
        return true
    }
}
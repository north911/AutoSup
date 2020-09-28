package com.example.autosup.model

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.R
import com.example.autosup.adapters.OnPartClickListener
import com.example.autosup.adapters.PartAdapter
import com.example.autosup.listeners.SearchViewListener
import com.example.autosup.utils.*
import kotlinx.android.synthetic.main.car_part_fragment.*
import kotlinx.android.synthetic.main.part_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartFragment : Fragment(), OnPartClickListener, OnBackPressed {

    companion object {
        fun newInstance() = PartFragment()
    }

    private lateinit var viewModel: PartViewModel
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.part_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PartViewModel::class.java)
        part_recycler_view.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        viewModelScope.launch {
            val response = viewModel.getAllParts(
                getValueFromPreviousFragment(this@PartFragment, "url")
            )
            val parts = convertHtmlElementsToArrayParts(
                getPartElements(response.await().body())
            )
            part_recycler_view.adapter =
                PartAdapter(parts, this@PartFragment).also {
                    activity?.findViewById<SearchView>(R.id.searchView)?.setOnQueryTextListener(
                        SearchViewListener(it)
                    )
                }
        }
    }

    override fun onItemClicked(part: Part) {
        println("test")
    }

    override fun onBackPressed(): Boolean {
        activity?.findViewById<TextView>(R.id.main_toolbar)?.apply {
            val value = text.toString().substringBeforeLast("->")
            text = value
        }
        return true
    }
}
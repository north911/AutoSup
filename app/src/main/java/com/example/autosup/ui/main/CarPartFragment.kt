package com.example.autosup.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.R
import com.example.autosup.adapters.CarPartAdapter
import com.example.autosup.adapters.OnCarPartClickListener
import com.example.autosup.databinding.CarPartFragmentBinding
import com.example.autosup.listeners.SearchViewListener
import com.example.autosup.model.CarPart
import com.example.autosup.utils.OnBackPressed
import com.example.autosup.utils.convertHtmlElementsToArrayCarParts
import com.example.autosup.utils.getCarPartElements
import com.example.autosup.utils.getValueFromPreviousFragment
import kotlinx.android.synthetic.main.car_part_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CarPartFragment : Fragment(), OnCarPartClickListener, OnBackPressed {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private lateinit var viewModel: CarPartViewModel
    private lateinit var binding: CarPartFragmentBinding

    companion object {
        fun newInstance() = CarPartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_part_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CarPartViewModel::class.java)
        carPart_recycler_view.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        viewModelScope.launch {
            val response = viewModel.getAllCarParts(
                getValueFromPreviousFragment(
                    this@CarPartFragment,
                    "url"
                )
            )
            val carParts = convertHtmlElementsToArrayCarParts(
                getCarPartElements(
                    response.await().body()
                )
            )
            carPart_recycler_view.adapter =
                CarPartAdapter(carParts, this@CarPartFragment).also {
                    activity?.findViewById<SearchView>(R.id.searchView)?.setOnQueryTextListener(
                        SearchViewListener(it)
                    )
                }
        }
    }

    override fun onItemClicked(car: CarPart) {
    }

    override fun onBackPressed(): Boolean {
        activity?.findViewById<TextView>(R.id.main_toolbar)?.apply {
            val value = text.toString().substringBeforeLast("->")
            text = value
        }
        return true
    }
}
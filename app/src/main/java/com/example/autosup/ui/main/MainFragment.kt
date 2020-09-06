package com.example.autosup.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.Adapters.CarBrandAdapter
import com.example.autosup.Adapters.OnItemClickListener
import com.example.autosup.Model.CarBrand
import com.example.autosup.R
import com.example.autosup.utils.convertHtmlElementsToArray
import com.example.autosup.utils.getElements
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(), OnItemClickListener {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        recyclerView = view.findViewById(R.id.group_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModelScope.launch {
            val response = viewModel.getAllCarBrands()
            val brands = convertHtmlElementsToArray(getElements(response.await().body()))
            recyclerView.adapter =
                CarBrandAdapter(brands, this@MainFragment)
        }
    }

    override fun onItemClicked(car: CarBrand) {
        println("test")
    }
}
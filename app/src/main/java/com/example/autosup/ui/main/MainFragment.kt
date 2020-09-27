package com.example.autosup.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.R
import com.example.autosup.adapters.CarBrandAdapter
import com.example.autosup.adapters.OnCarItemClickListener
import com.example.autosup.databinding.MainFragmentBinding
import com.example.autosup.listeners.SearchViewListener
import com.example.autosup.model.CarBrand
import com.example.autosup.utils.convertHtmlElementsToArrayCars
import com.example.autosup.utils.getCarsElements
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(), OnCarItemClickListener {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        car_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        viewModelScope.launch {
            val response = viewModel.getAllCarBrands()
            val brands = convertHtmlElementsToArrayCars(getCarsElements(response.await().body()))
            car_recycler_view.adapter =
                CarBrandAdapter(brands, this@MainFragment).also {
                    activity?.findViewById<SearchView>(R.id.searchView)?.setOnQueryTextListener(SearchViewListener(it))
                }
        }
    }

    override fun onItemClicked(car: CarBrand) {
        goToNextFragment(car)
    }

    private fun goToNextFragment(car: CarBrand) {
        activity?.findViewById<TextView>(R.id.main_toolbar)?.text = car.name
        val bundle = Bundle()
        bundle.putString("carUrl", car.url)
        val fragment: Fragment = SubBrandFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        fragment.arguments = bundle
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(this.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
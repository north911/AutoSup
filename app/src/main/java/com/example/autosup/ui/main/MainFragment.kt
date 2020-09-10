package com.example.autosup.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.Adapters.CarBrandAdapter
import com.example.autosup.Adapters.OnItemClickListener
import com.example.autosup.Model.CarBrand
import com.example.autosup.R
import com.example.autosup.databinding.MainFragmentBinding
import com.example.autosup.utils.convertHtmlElementsToArray
import com.example.autosup.utils.getElements
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(), OnItemClickListener {

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
        group_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModelScope.launch {
            val response = viewModel.getAllCarBrands()
            val brands = convertHtmlElementsToArray(getElements(response.await().body()))
            group_recycler_view.adapter =
                CarBrandAdapter(brands, this@MainFragment)
        }
    }

    override fun onItemClicked(car: CarBrand) {
        goToNextFragment(car.name)
    }

    private fun goToNextFragment(carName: String) {
        val bundle = Bundle()
        bundle.putString("car", carName)
        val fragment: Fragment = SubBrandFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        fragment.arguments = bundle
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(this.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
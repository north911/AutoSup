package com.example.autosup.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.Adapters.OnSubBrandItemClickListener
import com.example.autosup.Adapters.SubBrandAdapter
import com.example.autosup.Model.SubBrand
import com.example.autosup.R
import com.example.autosup.databinding.SubBrandFragmentBinding
import com.example.autosup.utils.convertHtmlElementsToArraySubCars
import com.example.autosup.utils.getSubCarsElements
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.sub_brand_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubBrandFragment : Fragment(), OnSubBrandItemClickListener {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    companion object {
        fun newInstance() = SubBrandFragment()
    }

    private lateinit var viewModel: SubBrandViewModel
    private lateinit var binding: SubBrandFragmentBinding
    private var carBrand: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SubBrandFragmentBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.sub_brand_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SubBrandViewModel::class.java)
        subBrand_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModelScope.launch {
            val response = viewModel.getAllCarSubBrands(getCarBrandFromPreviousPage())
            val brands = convertHtmlElementsToArraySubCars(getSubCarsElements(response.await().body()))
            subBrand_recycler_view.adapter =
                SubBrandAdapter(brands, this@SubBrandFragment)
        }
    }

    private fun getCarBrandFromPreviousPage(): String? {
        val bundle = this.arguments
        if (bundle != null) {
            carBrand = bundle.getString("carUrl")
        }
        return carBrand
    }

    override fun onItemClicked(car: SubBrand) {
        TODO("Not yet implemented")
    }
}
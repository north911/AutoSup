package com.example.autosup.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.adapters.OnSubBrandItemClickListener
import com.example.autosup.adapters.SubBrandAdapter
import com.example.autosup.model.SubBrand
import com.example.autosup.R
import com.example.autosup.databinding.SubBrandFragmentBinding
import com.example.autosup.utils.OnBackPressed
import com.example.autosup.utils.convertHtmlElementsToArraySubCars
import com.example.autosup.utils.getSubCarsElements
import com.example.autosup.utils.getValueFromPreviousFragment
import kotlinx.android.synthetic.main.sub_brand_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubBrandFragment : Fragment(), OnSubBrandItemClickListener, OnBackPressed {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    companion object {
        fun newInstance() = SubBrandFragment()
    }

    private lateinit var viewModel: SubBrandViewModel
    private lateinit var binding: SubBrandFragmentBinding

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
            val response = viewModel.getAllCarSubBrands(getValueFromPreviousFragment(this@SubBrandFragment,"carUrl"))
            val brands = convertHtmlElementsToArraySubCars(getSubCarsElements(response.await().body()))
            subBrand_recycler_view.adapter =
                SubBrandAdapter(brands, this@SubBrandFragment)
        }
    }


    override fun onItemClicked(subBrand: SubBrand) {
        goToNextFragment(subBrand)
    }

    private fun goToNextFragment(subBrand: SubBrand) {
        activity?.findViewById<TextView>(R.id.main_toolbar)?.apply {
            val value = text.toString().plus("->").plus(subBrand.name)
            text = value
        }
        val bundle = Bundle()
        bundle.putString("subUrl", subBrand.url)
        val fragment: Fragment = EngineFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        fragment.arguments = bundle
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(this.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed(): Boolean {
        activity?.findViewById<TextView>(R.id.main_toolbar)?.apply {
            val value = text.toString().substringBeforeLast("->")
            text = value
        }
        return true
    }
}
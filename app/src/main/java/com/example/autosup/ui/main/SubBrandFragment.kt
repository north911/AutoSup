package com.example.autosup.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.autosup.R
import com.example.autosup.databinding.MainFragmentBinding
import com.example.autosup.databinding.SubBrandFragmentBinding

class SubBrandFragment : Fragment() {

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
        getCarBrandFromPreviousPage()
        binding.testText.text = "hello"
    }

    private fun getCarBrandFromPreviousPage() {
        val bundle = this.arguments
        if (bundle != null) {
            carBrand = bundle.getString("car")
        }
    }

}
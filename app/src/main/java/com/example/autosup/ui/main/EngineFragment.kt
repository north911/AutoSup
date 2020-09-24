package com.example.autosup.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.R
import com.example.autosup.adapters.EngineAdapter
import com.example.autosup.adapters.OnEngineClickListener
import com.example.autosup.databinding.SubBrandFragmentBinding
import com.example.autosup.model.Engine
import com.example.autosup.utils.convertHtmlElementsToArrayEngines
import com.example.autosup.utils.getEngineElements
import com.example.autosup.utils.getValueFromPreviousFragment
import kotlinx.android.synthetic.main.engine_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EngineFragment : Fragment(), OnEngineClickListener {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    companion object {
        fun newInstance() = EngineFragment()
    }

    private lateinit var viewModel: EngineViewModel
    private lateinit var binding: SubBrandFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SubBrandFragmentBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.engine_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EngineViewModel::class.java)
        engine_recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModelScope.launch {
            val response = viewModel.getAllEngines(getValueFromPreviousFragment(this@EngineFragment,"subUrl"))
            val engines = convertHtmlElementsToArrayEngines(getEngineElements(response.await().body()))
            engine_recycler_view.adapter =
                EngineAdapter(engines, this@EngineFragment)
        }
    }

    override fun onItemClicked(car: Engine) {
        goToNextFragment(car.url)
    }

    private fun goToNextFragment(url: String) {
        val bundle = Bundle()
        bundle.putString("url", url)
        val fragment: Fragment = CarPartFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        fragment.arguments = bundle
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(this.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
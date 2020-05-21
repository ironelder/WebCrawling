package com.ironelder.withinno.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ironelder.withinno.R
import com.ironelder.withinno.component.MainRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.startWebCrawling()
        rv_image_list.apply {
            adapter = MainRecyclerAdapter()
        }
        viewModel.webCrawlingData.observe(viewLifecycleOwner, Observer {
            (rv_image_list.adapter as MainRecyclerAdapter).setData(it)
        })
    }
}
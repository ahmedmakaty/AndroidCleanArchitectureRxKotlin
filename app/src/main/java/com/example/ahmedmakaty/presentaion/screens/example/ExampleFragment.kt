package com.example.ahmedmakaty.presentaion.screens.example

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahmedmakaty.R
import com.example.ahmedmakaty.presentaion.BaseFragment
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ExampleFragment : BaseFragment() {

    lateinit var exampleViewModel: ExampleViewModel

    @Inject
    lateinit var exampleViewModelFactory: ExampleViewModelFactory

    companion object {
        fun newInstance() = ExampleFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        exampleViewModel = ViewModelProviders.of(this, exampleViewModelFactory).get(ExampleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_example, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exampleViewModel.operate()
    }
}
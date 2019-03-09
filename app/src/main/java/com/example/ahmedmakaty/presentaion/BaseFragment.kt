package com.example.ahmedmakaty.presentaion

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import dagger.android.support.DaggerFragment

open class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
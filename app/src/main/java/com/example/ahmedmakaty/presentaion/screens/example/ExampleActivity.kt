package com.example.ahmedmakaty.presentaion.screens.example

import com.example.ahmedmakaty.presentaion.BaseActivity
import com.example.ahmedmakaty.presentaion.BaseFragment

class ExampleActivity: BaseActivity() {

    override fun getFragment(): BaseFragment {
        return ExampleFragment.newInstance()
    }
}
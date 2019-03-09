package com.example.ahmedmakaty.presentaion

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.ahmedmakaty.R
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (supportFragmentManager.findFragmentById(R.id.main_content) == null) {
            var fragment: BaseFragment = getFragment()
            supportFragmentManager.beginTransaction().replace(R.id.main_content, fragment).addToBackStack(fragment.tag)
                .commit()

        }
    }

    abstract fun getFragment(): BaseFragment

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
package com.example.ahmedmakaty.presentaion

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.ahmedmakaty.R
import com.example.ahmedmakaty.helper.LocaleHelper
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

        val l = LocaleHelper.getInstance(baseContext)
        val country = getString(R.string.default_Country)

        l.setLocale(baseContext, country.toUpperCase(), l.language.toString())

        if (l.language.equals("ar")) {
            LocaleHelper.ChangeDesignToRTL(this)
        } else {
            LocaleHelper.ChangeDesignToLTR(this)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        fixAlignment()
    }

    fun fixAlignment() {
        val l = LocaleHelper.getInstance(baseContext)
        val country = getString(R.string.default_Country)
        l.setLocale(baseContext, country.toUpperCase(), l.language.toString())
    }

    override fun attachBaseContext(newBase: Context) {
        var newBase = newBase

        //Set Application local

        val l = LocaleHelper.getInstance(newBase)

        val country = newBase.getString(R.string.default_Country)

        newBase = l.setLocale(
            newBase, country.toUpperCase(), l
                .language.toString()
        )

        super.attachBaseContext(newBase)
    }

    override fun onResume() {
        super.onResume()

        val l = LocaleHelper.getInstance(baseContext)
        val country = getString(R.string.default_Country)
        l.setLocale(baseContext, country.toUpperCase(), l.language.toString())
    }

    abstract fun getFragment(): BaseFragment

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
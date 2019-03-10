package com.example.ahmedmakaty.helper

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.View
import com.example.ahmedmakaty.data.Constants
import java.util.*

class LocaleHelper private constructor(internal var context: Context) {
    internal var sharedPreferences: SharedPreferences

    val language: String?
        get() = sharedPreferences.getString(Constants.APP_LANGUAGE, "ar")

    init {
        sharedPreferences = getDefaultSharedPreferences(context)
    }

    fun setLocale(context: Context, country: String, language: String): Context {
        var context = context

        if (language == "ar")
            changeLanguage("ar")
        else
            changeLanguage("en")
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context = updateResources(context, country, language)
        } else {
            updateResources(country, language)
        }
        return context
    }

    fun changeLanguage(language: String) {
        sharedPreferences.edit().putString(Constants.APP_LANGUAGE, language).apply()
    }

    private fun updateResources(country: String, language: String) {
        val locale = Locale(language, country)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        //        configuration.locale = locale;
        configuration.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //            Locale l = Locale.getDefault();
            configuration.setLayoutDirection(Locale.getDefault())
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    companion object {
        private val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
        private var instance: LocaleHelper? = null

        fun getInstance(context: Context): LocaleHelper {
            if (instance == null) {
                instance = LocaleHelper(context)
            }
            return instance as LocaleHelper
        }

        private fun updateResources(context: Context, country: String, language: String): Context {
            var context = context
            val locale = Locale(language, country)
            Locale.setDefault(locale)

            val res = context.resources
            val config = Configuration(res.configuration)
            if (Build.VERSION.SDK_INT >= 17) {
                if (Build.VERSION.SDK_INT >= 24) {

                    val localeList = LocaleList(locale)
                    LocaleList.setDefault(localeList)
                    config.locales = localeList

                    config.setLocale(locale)
                    context = context.createConfigurationContext(config)
                } else {
                    config.setLocale(locale)
                    context = context.createConfigurationContext(config)
                }

            } else {
                config.locale = locale
                res.updateConfiguration(config, res.displayMetrics)
            }
            return context
        }

        val systemLanguage: String
            get() = Resources.getSystem().configuration.locale.language

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @SuppressLint("NewApi")
        fun ChangeDesignToRTL(activity: Activity) {

            val Lang = getDefaultSharedPreferences(activity).getString(Constants.APP_LANGUAGE, "ar")

            // This code change the design to support arabic lang.
            if (Lang != null && Lang == "ar") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (activity.window.decorView
                            .layoutDirection == View.LAYOUT_DIRECTION_LTR
                    ) {
                        activity.window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
                    }
                }
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @SuppressLint("NewApi")
        fun ChangeDesignToLTR(activity: Activity) {

            val Lang = getDefaultSharedPreferences(activity).getString(Constants.APP_LANGUAGE, "ar")

            // This code change the design to support arabic lang.
            if (Lang != null && Lang == "en") {
                // This code change the design to support arabic lang.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

                    if (activity.window.decorView.layoutDirection == View.LAYOUT_DIRECTION_RTL) {

                        activity.window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR

                    }
                }
            }
        }

        val isRTL: Boolean
            get() = isRTL(Locale.getDefault())

        private fun isRTL(locale: Locale): Boolean {
            val directionality = Character.getDirectionality(locale.displayName[0]).toInt()
            return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT.toInt() || directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC.toInt()
        }
    }
}
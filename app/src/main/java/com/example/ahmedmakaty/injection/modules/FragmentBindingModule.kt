package com.example.ahmedmakaty.injection.modules

import com.example.ahmedmakaty.injection.modules.screens.ExampleScreenModule
import com.example.ahmedmakaty.injection.scopes.PerActivity
import com.example.ahmedmakaty.presentaion.screens.example.ExampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [ExampleScreenModule::class])
    abstract fun bindExampleScreen(): ExampleFragment
}
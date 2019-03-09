package com.example.ahmedmakaty.presentaion.screens.example

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.ahmedmakaty.domain.interactor.example.GetExampleUseCase
import javax.inject.Inject

class ExampleViewModelFactory @Inject constructor(var exampleUseCase: GetExampleUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExampleViewModel::class.java!!)) {
            ExampleViewModel(exampleUseCase) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
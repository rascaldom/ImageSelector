package com.kakaobank.imageselector.di

import com.kakaobank.imageselector.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(searchUseCase = get())
    }

}
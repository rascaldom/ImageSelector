package com.kakaobank.imageselector.di

import domain.usecase.SearchUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single {
        SearchUseCase(repository = get())
    }

}
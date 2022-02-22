package com.kakaobank.imageselector.di

import data.repository.RepositoryImpl
import domain.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {

    single<Repository> {
        RepositoryImpl(remoteDataSource = get())
    }

}
package com.kakaobank.imageselector.di

import data.datasource.RemoteDataSource
import data.datasource.RemoteDataSourceImpl
import data.remote.api.SearchApi
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule = module {

    single {
        get<Retrofit>().create(SearchApi::class.java)
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(searchApi = get())
    }

}
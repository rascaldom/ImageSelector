package com.kakaobank.imageselector.di

import com.kakaobank.imageselector.ui.BookmarksAdapter
import com.kakaobank.imageselector.ui.BookmarksFragment
import com.kakaobank.imageselector.ui.ViewListAdapter
import com.kakaobank.imageselector.ui.ViewListFragment
import org.koin.dsl.module

val appModule = module {

    factory {
        ViewListFragment()
    }

    factory {
        BookmarksFragment()
    }

    factory {
        ViewListAdapter()
    }

    factory {
        BookmarksAdapter()
    }

}
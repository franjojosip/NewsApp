package ht.fjjukic.home.di

import ht.fjjukic.home.router.IndexRouter
import ht.fjjukic.home.viewmodel.IndexVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val indexModule = module {
    factory { IndexRouter() }
    viewModel{
        IndexVM(get(), get(), get())
    }
}
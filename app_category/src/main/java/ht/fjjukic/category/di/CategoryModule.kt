package ht.fjjukic.category.di

import ht.fjjukic.category.router.CategoryRouter
import ht.fjjukic.category.viewmodel.CategoryVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoryModule = module {
    factory { CategoryRouter() }
    viewModel {
        CategoryVM(get(), get(), get())
    }
}
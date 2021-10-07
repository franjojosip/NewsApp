package ht.ferit.fjjukic.menu.di

import ht.ferit.fjjukic.menu.router.MenuRouter
import ht.ferit.fjjukic.menu.viewmodel.MenuVM
import org.koin.dsl.module

val menuModule = module {
    factory { MenuRouter() }
    single { MenuVM(get(), get()) }
}
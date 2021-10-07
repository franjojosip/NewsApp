package ht.ferit.fjjukic.article.di

import ht.ferit.fjjukic.article.router.ArticleRouter
import ht.ferit.fjjukic.article.viewmodel.ArticleVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleModule = module {
    factory { ArticleRouter() }
    viewModel {
        ArticleVM(get(), get(), get())
    }
}
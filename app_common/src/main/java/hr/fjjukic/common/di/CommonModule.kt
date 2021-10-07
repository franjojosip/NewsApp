package hr.fjjukic.common.di

import hr.fjjukic.common.repository.article.ArticleRepositoryImpl
import hr.fjjukic.common.repository.category.CategoryRepositoryImpl
import hr.fjjukic.common.repository.menu.MenuRepositoryImpl
import hr.fjjukic.common.repository.news.NewsRepositoryImpl
import hr.fjjukic.common.restinterfaces.ApiRestInterface
import org.koin.dsl.module
import retrofit2.Retrofit

val commonModule = module {
    single { get<Retrofit>().create(ApiRestInterface::class.java) }
    factory { MenuRepositoryImpl(get()) }
    factory { ArticleRepositoryImpl(get()) }
    factory { NewsRepositoryImpl(get()) }
    factory { CategoryRepositoryImpl(get()) }
}
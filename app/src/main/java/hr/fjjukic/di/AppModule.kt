package hr.fjjukic.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hr.fjjukic.common.adapter.AppScreenAdapter
import hr.fjjukic.common.adapter.AppScreenAdapterImpl
import hr.fjjukic.common.repository.ResourceRepository
import hr.fjjukic.common.repository.ResourcesRepositoryImpl
import hr.fjjukic.common.router.AppRouter
import hr.fjjukic.common.router.AppRouterImpl
import hr.fjjukic.common.sharedpref.AppSharedPreference
import hr.fjjukic.error.AppErrorHandlerImpl
import hr.fjjukic.network.TokenAuthenticator
import hr.fjjukic.network.provideRetrofit
import hr.fjjukic.sharedpref.AppSharedPreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<Gson> { GsonBuilder().create() }
    single<AppSharedPreference> { AppSharedPreferencesImpl(get(), get()) }
    single { AppErrorHandlerImpl(get()) }
    single {
        val sharedPreference = get<AppSharedPreference>()
        provideRetrofit(
            sharedPreference,
            TokenAuthenticator.provideTokenAuthenticator(sharedPreference)
        )
    }
    factory<AppRouter> { AppRouterImpl() }
    factory<AppScreenAdapter> { AppScreenAdapterImpl() }
    factory<ResourceRepository> { ResourcesRepositoryImpl(androidContext().resources) }
}

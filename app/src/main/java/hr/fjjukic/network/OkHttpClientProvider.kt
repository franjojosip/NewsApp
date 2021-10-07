package hr.fjjukic.network

import hr.fjjukic.BuildConfig
import hr.fjjukic.common.sharedpref.AppSharedPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

fun provideOkHttpClient(
    sharedPreference: AppSharedPreference,
    tokenAuthenticator: TokenAuthenticator?
): OkHttpClient {
    val builder = OkHttpClient.Builder()
    tokenAuthenticator?.apply { builder.authenticator(this) }
    return builder.addInterceptor(HeaderInterceptor(sharedPreference))
        .apply {
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
            }
        }
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .build()
}

private const val CONNECTION_TIMEOUT = 30000L
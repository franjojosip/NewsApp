package hr.fjjukic.network

import hr.fjjukic.common.sharedpref.AppSharedPreference
import okhttp3.Interceptor
import okhttp3.Response

internal class HeaderInterceptor(private val sharedPreference: AppSharedPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
            .addHeader(ACCEPT, JSON)
            .apply {
                sharedPreference.authToken?.let {
                    addHeader(AUTHORIZATION, "$BEARER $it")
                }
                val locale = sharedPreference.appLocale
                if (locale.isNotEmpty()) {
                    addHeader(LANGUAGE, locale)
                }
            }
            .build())

    }
}

internal const val BEARER = "Bearer"
internal const val AUTHORIZATION = "Authorization"
internal const val ACCEPT = "Accept"
internal const val JSON = "application/json"
internal const val LANGUAGE = "Accept-Language"
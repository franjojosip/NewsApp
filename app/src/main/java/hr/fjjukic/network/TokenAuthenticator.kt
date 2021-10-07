package hr.fjjukic.network

import hr.fjjukic.common.restinterfaces.TokenRefreshRestInterface
import hr.fjjukic.common.sharedpref.AppSharedPreference
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val sharedPreference: AppSharedPreference,
    private val restInterface: TokenRefreshRestInterface
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == 401 && !sharedPreference.authToken.isNullOrEmpty()) {
            if (response.request.header(AUTHORIZATION) == "$BEARER ${sharedPreference.authToken}") {
                val refreshToken = sharedPreference.refreshToken ?: ""

                val retrofitResponse = restInterface.refreshToken(refreshToken).execute()
                val refreshTokenResponse = retrofitResponse.body()

                if (refreshTokenResponse != null) {
                    sharedPreference.authToken = refreshTokenResponse.token
                    sharedPreference.refreshToken = refreshTokenResponse.refreshToken

                    return response.request
                        .newBuilder()
                        .header(AUTHORIZATION, "$BEARER ${sharedPreference.authToken}")
                        .build()
                }

            } else {
                return response.request
                    .newBuilder()
                    .header(AUTHORIZATION, "$BEARER ${sharedPreference.authToken}")
                    .build()
            }
        }
        return null
    }

    companion object {
        fun provideTokenAuthenticator(appSharedPreferences: AppSharedPreference): TokenAuthenticator {
            val retrofit = provideRetrofit(appSharedPreferences, null)
            val service = retrofit.create(TokenRefreshRestInterface::class.java)
            return TokenAuthenticator(appSharedPreferences, service)
        }
    }
}
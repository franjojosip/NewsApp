package hr.fjjukic.common.restinterfaces

import hr.fjjukic.common.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenRefreshRestInterface {
    @FormUrlEncoded
    @POST("api/token/refresh")
    fun refreshToken(
        @Field("refresh_token") refreshToken: String
    ): Call<LoginResponse>

}
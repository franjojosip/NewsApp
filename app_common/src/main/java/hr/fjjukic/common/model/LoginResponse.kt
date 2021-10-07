package hr.fjjukic.common.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_at")
    var _expiresAt: Long
)

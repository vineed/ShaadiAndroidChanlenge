package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LoginDTO(
    @SerializedName("md5")
    val md5: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("salt")
    val salt: String? = null,
    @SerializedName("sha1")
    val sha1: String? = null,
    @SerializedName("sha256")
    val sha256: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("uuid")
    val uuid: String? = null
)
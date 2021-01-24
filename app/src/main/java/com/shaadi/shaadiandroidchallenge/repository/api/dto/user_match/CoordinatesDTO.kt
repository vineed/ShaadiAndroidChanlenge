package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CoordinatesDTO(
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null
)
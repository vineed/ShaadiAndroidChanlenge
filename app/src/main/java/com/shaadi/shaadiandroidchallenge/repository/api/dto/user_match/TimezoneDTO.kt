package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TimezoneDTO(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("offset")
    val offset: String? = null
)
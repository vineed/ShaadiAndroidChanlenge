package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class StreetDTO(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("number")
    val number: Int? = null
)
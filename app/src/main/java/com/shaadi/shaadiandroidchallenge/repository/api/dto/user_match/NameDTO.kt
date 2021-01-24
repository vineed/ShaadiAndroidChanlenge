package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class NameDTO(
    @SerializedName("first")
    val first: String? = null,
    @SerializedName("last")
    val last: String? = null,
    @SerializedName("title")
    val title: String? = null
)
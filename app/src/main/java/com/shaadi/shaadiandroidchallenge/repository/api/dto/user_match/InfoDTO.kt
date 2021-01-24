package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class InfoDTO(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: Int? = null,
    @SerializedName("seed")
    val seed: String? = null,
    @SerializedName("version")
    val version: String? = null
)
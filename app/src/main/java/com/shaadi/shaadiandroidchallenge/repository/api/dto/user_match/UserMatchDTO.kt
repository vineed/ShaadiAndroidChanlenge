package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserMatchDTO(
    @SerializedName("info")
    val info: InfoDTO? = null,
    @SerializedName("results")
    val results: List<ResultDTO>? = null
)
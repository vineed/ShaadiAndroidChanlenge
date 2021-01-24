package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RegisteredDTO(
    @SerializedName("age")
    val age: Int? = null,
    @SerializedName("date")
    val date: String? = null
)
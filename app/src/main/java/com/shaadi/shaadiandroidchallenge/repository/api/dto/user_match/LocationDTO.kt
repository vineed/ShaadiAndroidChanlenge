package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LocationDTO(
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("coordinates")
    val coordinates: CoordinatesDTO? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("postcode")
    val postcode: Any? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("street")
    val street: StreetDTO? = null,
    @SerializedName("timezone")
    val timezone: TimezoneDTO? = null
)
package com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResultDTO(
    @SerializedName("cell")
    val cell: String? = null,
    @SerializedName("dob")
    val dob: DobDTO? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("id")
    val id: IdDTO? = null,
    @SerializedName("location")
    val location: LocationDTO? = null,
    @SerializedName("login")
    val login: LoginDTO? = null,
    @SerializedName("name")
    val name: NameDTO? = null,
    @SerializedName("nat")
    val nat: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("picture")
    val picture: PictureDTO? = null,
    @SerializedName("registered")
    val registered: RegisteredDTO? = null
)
package com.shaadi.shaadiandroidchallenge.partner_match.model

data class UserMatch(
    val uuid: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val thumbnail: String = "",
    val mediumImage: String = "",
    val largeImage: String = "",
    var isAccepted: Boolean? = null,
    var animateIsAccepted: Boolean = false
) {
    val displayName
        get() = firstName.trim().let { trimmedFirstName ->
            "$trimmedFirstName${
                if (trimmedFirstName.isNotBlank()) lastName[0].toUpperCase()
                    .let { " $it" } else lastName
            }"
        }

    val shortDesc
        get() = "${
            age.let {
                if (it < 0) "" else it
            }.toString().appendCommaNotBlank()
        }${street.appendCommaNotBlank()}${city.appendCommaNotBlank()}${state.appendCommaNotBlank()}${country}"
}

fun String.appendCommaNotBlank(): String {
    return this.trim().let { if (it.isNotBlank()) "$this, " else it }
}
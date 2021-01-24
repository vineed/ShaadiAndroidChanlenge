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
) {
    val displayName
        get() = "$firstName ${if (firstName.isNotBlank()) lastName[0].toUpperCase() else lastName}"

    val shortDesc
        get() = "${
            age.let {
                if (it < 0) "" else it
            }.toString().appendCommaNotBlank()
        }${city.appendCommaNotBlank()}${state.appendCommaNotBlank()}${country}"
}

fun String.appendCommaNotBlank(): String {
    return if (this.isNotBlank()) "$this, " else ""
}
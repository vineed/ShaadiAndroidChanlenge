package com.shaadi.shaadiandroidchallenge.partner_match.model

data class UserMatch(
    val uuid: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val isAccepted: Boolean? = null
)
package com.shaadi.shaadiandroidchallenge.partner_match.model

data class UserMatch(
    val uuid: String,
    val title:String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val isAccepted: Boolean? = null
)
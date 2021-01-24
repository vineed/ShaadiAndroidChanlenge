package com.shaadi.shaadiandroidchallenge.partner_match.ext

import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match.ResultDTO
import com.shaadi.shaadiandroidchallenge.repository.db.entities.UserMatchEntity

//db->domain
fun UserMatchEntity.asUserMatch() = UserMatch(
    uuid = this.uuid,
    title = this.title,
    firstName = this.first_name,
    lastName = this.last_name,
    age = this.age,
    street = this.street,
    city = this.city,
    state = this.state,
    country = this.country,
    thumbnail = this.thumbnail,
    mediumImage = this.mediumImage,
    largeImage = this.largeImage,
    isAccepted = this.isAccepted
)

//api->db
fun ResultDTO.asUserMatchEntity(): UserMatchEntity? {
    return UserMatchEntity(
        uuid = this.login?.uuid ?: return null,
        title = this.name?.title ?: "",
        first_name = this.name?.first ?: "",
        last_name = this.name?.last ?: "",
        age = this.dob?.age ?: -1,
        street = this.location?.street?.name ?: "",
        city = this.location?.city ?: "",
        state = this.location?.state ?: "",
        country = this.location?.country ?: "",
        thumbnail = this.picture?.thumbnail ?: "",
        mediumImage = this.picture?.medium ?: "",
        largeImage = this.picture?.large ?: "",
    )
}

//domain->db
fun UserMatch.asUserMatchEntity() = UserMatchEntity(
    uuid = this.uuid,
    title = this.title,
    first_name = this.firstName,
    last_name = this.lastName,
    age = this.age,
    street = this.street,
    city = this.city,
    state = this.state,
    country = this.country,
    thumbnail = this.thumbnail,
    mediumImage = this.mediumImage,
    largeImage = this.largeImage,
    isAccepted = this.isAccepted
)

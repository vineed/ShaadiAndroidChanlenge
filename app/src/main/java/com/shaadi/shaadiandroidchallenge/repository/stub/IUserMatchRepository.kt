package com.shaadi.shaadiandroidchallenge.repository.stub

import com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match.UserMatchDTO
import com.shaadi.shaadiandroidchallenge.repository.model.ApiOutput
import kotlinx.coroutines.flow.Flow

interface IUserMatchRepository {
    suspend fun getAllMatchUsers(): Flow<ApiOutput<UserMatchDTO>>
}
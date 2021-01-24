package com.shaadi.shaadiandroidchallenge.repository.stub

import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.model.Result
import kotlinx.coroutines.flow.Flow

interface IUserMatchRepository {
    suspend fun getAllMatchUsers(): Flow<Result<List<UserMatch>>>
    suspend fun updateUserAcceptedStatus(userMatch: UserMatch): Flow<Result<Nothing?>>
}
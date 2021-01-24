package com.shaadi.shaadiandroidchallenge.repository.impl

import com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match.UserMatchDTO
import com.shaadi.shaadiandroidchallenge.repository.model.ApiOutput
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class UserMatchRepository : IUserMatchRepository {
    override suspend fun getAllMatchUsers(): Flow<ApiOutput<UserMatchDTO>> =
        flow<ApiOutput<UserMatchDTO>> {
            Timber.d("Thread in ${Thread.currentThread()}")
            emit(ApiOutput.Network.Success("test", 200, UserMatchDTO()))
        }
}
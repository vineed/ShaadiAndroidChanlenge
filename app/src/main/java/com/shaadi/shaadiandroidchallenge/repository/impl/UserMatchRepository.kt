package com.shaadi.shaadiandroidchallenge.repository.impl

import com.shaadi.shaadiandroidchallenge.repository.api.dto.user_match.UserMatchDTO
import com.shaadi.shaadiandroidchallenge.repository.api.stub.IShaadiApi
import com.shaadi.shaadiandroidchallenge.repository.model.ApiOutput
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class UserMatchRepository(val shaadiApi: IShaadiApi) : BaseRepository(), IUserMatchRepository {
    override suspend fun getAllMatchUsers(): Flow<ApiOutput<UserMatchDTO>> =
        flow {
            emit(parseApiCall { shaadiApi.getAllUserMatch("10", "1") })
        }
}
package com.shaadi.shaadiandroidchallenge.repository.impl

import com.shaadi.shaadiandroidchallenge.partner_match.ext.asUserMatch
import com.shaadi.shaadiandroidchallenge.partner_match.ext.asUserMatchEntity
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.api.stub.IShaadiApi
import com.shaadi.shaadiandroidchallenge.repository.db.dao.impl.UserMatchDao
import com.shaadi.shaadiandroidchallenge.repository.db.stub.IShaadiDatabase
import com.shaadi.shaadiandroidchallenge.repository.impl.core.Constants
import com.shaadi.shaadiandroidchallenge.repository.model.Result
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class UserMatchRepository(
    private val shaadiApi: IShaadiApi,
    private val shaadiDatabase: IShaadiDatabase
) : BaseRepository(), IUserMatchRepository {

    private val userMatchDao: UserMatchDao by lazy { shaadiDatabase.getUserMatchDao() }

    override suspend fun getAllMatchUsers(): Flow<Result<List<UserMatch>>> =
        flow {

            emit(
                Result.Success.DBSource(
                    body = userMatchDao.getAllMatchUser().map { it.asUserMatch() }
                )
            )

            flowApiCall { shaadiApi.getAllUserMatch("10", "1") }
                .collect { userMatchDTO ->
                    val mappedMatchUserEntities =
                        userMatchDTO.results?.mapNotNull { it.asUserMatchEntity() }

                    userMatchDao.addAllMatchUser(mappedMatchUserEntities ?: emptyList())

                    emit(
                        Result.Success.APISource(
                            body = userMatchDao.getAllMatchUser().map { it.asUserMatch() })
                    )
                }
        }

    override suspend fun updateUserAcceptedStatus(userMatch: UserMatch): Flow<Result<Nothing?>> =
        flow {
            val updateSt = userMatchDao.updateMatchUser(userMatch.asUserMatchEntity())

            emit(
                if (updateSt > 0) Result.Success.DBSource<Nothing?>(body = null)
                else Result.Failure(failureMsg = Constants.ERROR_OCCURRED)
            )
        }
}
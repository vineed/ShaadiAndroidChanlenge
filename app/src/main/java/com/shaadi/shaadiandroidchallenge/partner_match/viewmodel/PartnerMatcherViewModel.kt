package com.shaadi.shaadiandroidchallenge.partner_match.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shaadi.shaadiandroidchallenge.core.base.viewmodel.BaseViewModel
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.model.Result
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

sealed class PartnerMatcherViewEvent {
    class MatchItemChange(val pos: Int, val userMatch: UserMatch) : PartnerMatcherViewEvent()
}

class PartnerMatcherViewModel(private val userMatchRepository: IUserMatchRepository) :
    BaseViewModel<PartnerMatcherViewEvent>() {

    private val _userMatchListLiveData = MutableLiveData<List<UserMatch>>(mutableListOf())
    val userMatchListLiveData: LiveData<List<UserMatch>>
        get() = _userMatchListLiveData

    private var _userMatchList: List<UserMatch>? = null
        set(value) {
            _userMatchListLiveData.value = value
            field = value
        }

    init {
        retrieveAllMatchUsers()
    }

    fun retrieveAllMatchUsers() {
        viewModelScope.launch {
            showLoader("Loading data...")
            try {

                userMatchRepository.getAllMatchUsers()
                    /*//TODO Experimental at this point
                    .onCompletion { cause ->
                        hideLoader()

                    }*/
                    .collect {
                        when (it) {
                            is Result.Success -> {
                                hideLoader()
                                _userMatchList = it.body
                            }
                            is Result.Failure -> showToast(it.failureMsg)
                        }
                    }
            } catch (ex: IOException) {
                networkWrong()
            } catch (ex: java.lang.Exception) {
                wentWrong()
            } finally {
                hideLoader()
            }
        }
    }

    fun updateUserAcceptStatus(userMatch: UserMatch) {
        viewModelScope.launch {
            showLoader("Please wait...")
            try {
                userMatchRepository.updateUserAcceptedStatus(userMatch).collect { result ->
                    when (result) {
                        is Result.Success -> {
                            userMatch.isAccepted?.let { isAccepted ->
                                val pos = _userMatchList?.indexOf(userMatch) ?: -1

                                if (pos < 0) {
                                    wentWrong()
                                } else {
                                    fireEvent(
                                        PartnerMatcherViewEvent.MatchItemChange(
                                            pos,
                                            userMatch
                                        )
                                    )
                                    showToast(if (isAccepted) "Member accepted!" else "Member recjected!")
                                }
                            }
                        }
                        is Result.Failure -> showToast("Failed to update value")
                    }
                }
            } catch (ex: Exception) {
                wentWrong()
            } finally {
                hideLoader()
            }
        }
    }
}
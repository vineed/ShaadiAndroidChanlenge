package com.shaadi.shaadiandroidchallenge.partner_match.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shaadi.shaadiandroidchallenge.core.base.viewmodel.BaseViewModel
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.model.Result
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

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

    private val _isApiFetching = MutableLiveData<Boolean>(false)
    val isApiFetching: LiveData<Boolean>
        get() = _isApiFetching

    init {
        retrieveAllMatchUsers(true)
    }

    fun retrieveAllMatchUsers(loadCache: Boolean = true) {
        viewModelScope.launch {
            userMatchRepository.getAllMatchUsers(loadCache)
                .onStart {
                    if (loadCache) showLoader("Please wait...")
                    _isApiFetching.value = true
                }
                .defaultNetworkCatch {
                    hideLoader()
                    _isApiFetching.value = false
                }
                .collect {
                    if (loadCache) hideLoader()

                    when (it) {
                        is Result.Success -> {
                            _userMatchList = it.body

                            if (it is Result.Success.APISource) _isApiFetching.value = false
                        }
                        is Result.Failure -> {
                            showToast(it.failureMsg)
                        }
                    }
                }
        }
    }

    fun updateUserAcceptStatus(userMatch: UserMatch) {
        viewModelScope.launch {
            userMatchRepository.updateUserAcceptedStatus(userMatch)
                .defaultNetworkCatch { hideLoader() }
                .onStart { showLoader("Please wait...") }
                .collect { result ->
                    hideLoader()

                    when (result) {
                        is Result.Success -> {
                            userMatch.isAccepted?.let {
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
                                    //showToast(if (isAccepted) "Member accepted!" else "Member rejected!")
                                }
                            }
                        }
                        is Result.Failure -> showToast(result.failureMsg)
                    }
                }
        }
    }
}
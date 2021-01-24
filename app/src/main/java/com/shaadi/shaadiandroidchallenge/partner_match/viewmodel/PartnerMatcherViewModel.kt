package com.shaadi.shaadiandroidchallenge.partner_match.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shaadi.shaadiandroidchallenge.core.base.viewmodel.BaseViewModel
import com.shaadi.shaadiandroidchallenge.core.lifecycle_ext.notifyLiveData
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.impl.core.Constants
import com.shaadi.shaadiandroidchallenge.repository.model.Result
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

sealed class PartnerMatcherViewEvent {

}

class PartnerMatcherViewModel(private val userMatchRepository: IUserMatchRepository) :
    BaseViewModel<PartnerMatcherViewEvent>() {

    private val _userMatchListLiveData = MutableLiveData<List<UserMatch>>(mutableListOf())
    val userMatchListLiveData: LiveData<List<UserMatch>>
        get() = _userMatchListLiveData

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
                                _userMatchListLiveData.value = it.body
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
                                _userMatchListLiveData.notifyLiveData { }
                                showToast(if (isAccepted) "Member accepted!" else "Member recjected!")
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
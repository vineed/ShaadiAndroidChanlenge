package com.shaadi.shaadiandroidchallenge.partner_match.viewmodel

import androidx.lifecycle.viewModelScope
import com.shaadi.shaadiandroidchallenge.core.base.viewmodel.BaseViewModel
import com.shaadi.shaadiandroidchallenge.partner_match.model.UserMatch
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class PartnerMatcherViewEvent {

}

class PartnerMatcherViewModel(private val userMatchRepository: IUserMatchRepository) :
    BaseViewModel<PartnerMatcherViewEvent>() {

    fun retrieveAllMatchUsers() {
        viewModelScope.launch {
            showLoader("Loading data...")
            try {

                userMatchRepository.getAllMatchUsers()
                    /*//TODO Experimental at this point, when done will remove try finally
                    .onCompletion { cause ->
                        hideLoader()

                    }*/
                    .collect {
                        Timber.d("Got value $it")
                    }
            } catch (ex: Exception) {
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
                userMatchRepository.updateUserAcceptedStatus(userMatch)
            } catch (ex: Exception) {
                wentWrong()
            } finally {
                hideLoader()
            }
        }
    }
}
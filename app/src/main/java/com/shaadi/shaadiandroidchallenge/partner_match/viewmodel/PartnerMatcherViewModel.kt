package com.shaadi.shaadiandroidchallenge.partner_match.viewmodel

import androidx.lifecycle.viewModelScope
import com.shaadi.shaadiandroidchallenge.core.base.viewmodel.BaseViewModel
import com.shaadi.shaadiandroidchallenge.repository.stub.IUserMatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class PartnerMatcherViewEvent {

}

class PartnerMatcherViewModel(private val userMatchRepository: IUserMatchRepository) :
    BaseViewModel<PartnerMatcherViewEvent>() {

    fun retrieveAllMatchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userMatchRepository.getAllMatchUsers().collect {
                Timber.d("Got value $it")
            }
        }
    }
}
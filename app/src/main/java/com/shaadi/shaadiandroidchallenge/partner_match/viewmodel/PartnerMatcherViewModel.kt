package com.shaadi.shaadiandroidchallenge.partner_match.viewmodel

import androidx.lifecycle.viewModelScope
import com.shaadi.shaadiandroidchallenge.core.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class PartnerMatcherViewEvent {

}

class PartnerMatcherViewModel : BaseViewModel<PartnerMatcherViewEvent>() {

}
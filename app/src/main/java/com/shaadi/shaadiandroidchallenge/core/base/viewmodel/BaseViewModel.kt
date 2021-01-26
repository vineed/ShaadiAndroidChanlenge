package com.shaadi.shaadiandroidchallenge.core.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaadi.shaadiandroidchallenge.core.Constants
import com.shaadi.shaadiandroidchallenge.core.lifecycle_ext.notifyLiveData
import com.shaadi.shaadiandroidchallenge.core.models.ProgressModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.shaadi.shaadiandroidchallenge.core.lifecycle.SingleLiveEvent;
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.io.IOException

abstract class BaseViewModel<EV> : ViewModel() {
    var doLoading = MutableLiveData<ProgressModel>().apply { this.value = ProgressModel() }
    var toastLiveData = SingleLiveEvent<String>()

    var isNetworkOnlineAction: (() -> Boolean)? = null

    private val _viewEventLiveData = SingleLiveEvent<EV>()
    val viewEventLiveData: LiveData<EV> = _viewEventLiveData

    protected fun fireEvent(event: EV) {
        _viewEventLiveData.postValue(event)
    }

    suspend fun showLoader(message: String = Constants.MESSAGE.PLEASE_WAIT) =
        withContext(Dispatchers.Main) {
            doLoading.notifyLiveData {
                this.message = message
                doLoading = true
            }
        }

    suspend fun hideLoader() = withContext(Dispatchers.Main) {
        doLoading.notifyLiveData { doLoading = false }
    }

    fun showToast(message: String) {
        toastLiveData.value = message
    }

    fun asyncShowToast(message: String) {
        toastLiveData.postValue(message)
    }

    fun wentWrong() {
        showToast(Constants.ERROR.ERROR_OCCURRED)
    }

    fun networkWrong() {
        showToast(Constants.ERROR.CHECK_NETWORK)
    }


    fun <T> Flow<T>.defaultNetworkCatch(action: (suspend (cause: Throwable) -> Unit)? = null): Flow<T> {
        return catch { cause ->
            when (cause) {
                is HttpException ->
                    showToast(cause.message ?: Constants.ERROR.ERROR_OCCURRED)
                is IOException -> networkWrong()
                is Exception -> wentWrong()
            }

            action?.invoke(cause)
        }
    }
}
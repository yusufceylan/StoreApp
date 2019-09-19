package tr.com.storeapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import tr.com.storeapp.network.core.ApiException
import tr.com.storeapp.utils.NetworkState
import tr.com.storeapp.utils.SingleLiveEvent

/**
 * Base Class for [ViewModel] classes
 */
abstract class BaseViewModel(
    private val disposable: CompositeDisposable
) : ViewModel() {

    private var networkStatus: SingleLiveEvent<NetworkState> = SingleLiveEvent()
    private val error : SingleLiveEvent<ApiException> = SingleLiveEvent()


    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun getNetworkStatus(): LiveData<NetworkState>? {
        return networkStatus
    }

    fun setNetworkStatus(networkState: NetworkState) {
        networkStatus.value = networkState
    }

    fun getError(): LiveData<ApiException>? {
        return error
    }

    fun setError(apiException: ApiException) {
        error.value = apiException
    }

}
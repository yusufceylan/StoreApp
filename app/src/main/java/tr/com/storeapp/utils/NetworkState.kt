package tr.com.storeapp.utils

import androidx.annotation.Nullable

/**
 * Class that hold network state
 */
class NetworkState(val status: Status, @Nullable message: String?) : Exception(message) {

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED,
        NO_ITEM,
        FIRST_ITEM
    }

    companion object {

        val LOADED: NetworkState
        val LOADING: NetworkState
        val FAILED: NetworkState
        val NO_ITEM: NetworkState
        val FIRST_ITEM: NetworkState

        init {
            LOADING = NetworkState(Status.RUNNING, "")
            LOADED = NetworkState(Status.SUCCESS, "")
            FAILED = NetworkState(Status.FAILED, "")
            NO_ITEM = NetworkState(Status.NO_ITEM, "")
            FIRST_ITEM = NetworkState(Status.FIRST_ITEM, "")
        }
    }
}
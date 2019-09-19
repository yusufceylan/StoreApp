package tr.com.storeapp.network.core

/**
 * Exception Class that handles Api Exceptions
 */
data class ApiException(override var message: String?, var description: String?, var code: Int?) : Exception(message)
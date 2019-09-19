package tr.com.storeapp.network.repository

import io.reactivex.Single

/**
 * Interface that handles all filtered methods
 */
interface ApiRepository {

    fun fetchProductList(params : HashMap<String, Any>?) : Single<Any>
}
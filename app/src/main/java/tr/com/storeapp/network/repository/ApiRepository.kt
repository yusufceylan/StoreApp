package tr.com.storeapp.network.repository

import io.reactivex.Single
import tr.com.storeapp.network.data.ProductItem

/**
 * Interface that handles all filtered methods
 */
interface ApiRepository {

    fun fetchProductList(params : HashMap<String, Any>?) : Single<MutableList<ProductItem>>
}
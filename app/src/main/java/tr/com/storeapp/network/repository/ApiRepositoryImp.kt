package tr.com.storeapp.network.repository

import io.reactivex.Single
import tr.com.storeapp.network.data.ProductItem

/**
 * Implementation class of [ApiHelper] that manage requests
 */
class ApiRepositoryImp constructor(private val apiHelper: ApiHelper) : ApiRepository {

    override fun fetchProductList(params: HashMap<String, Any>?): Single<MutableList<ProductItem>> {
        return apiHelper.fetchProductList(params)
    }

}
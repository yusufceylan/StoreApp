package tr.com.storeapp.network.repository

import io.reactivex.Single

/**
 * Implementation class of [ApiHelper] that manage requests
 */
class ApiRepositoryImp constructor(private val apiHelper: ApiHelper) : ApiRepository {

    override fun fetchProductList(params: HashMap<String, Any>?): Single<Any> {
        return apiHelper.fetchProductList(params)
    }

}
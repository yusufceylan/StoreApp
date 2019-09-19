package tr.com.storeapp.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Main services that handles all endpoint processes
 */
interface ApiService {

    /**
     * Fetch product list from service
     */
    @GET
    fun fetchProductList() : Single<Response<Any>>
}
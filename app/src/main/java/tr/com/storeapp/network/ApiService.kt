package tr.com.storeapp.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import tr.com.storeapp.network.data.ProductItem

/**
 * Main services that handles all endpoint processes
 */
interface ApiService {

    /**
     * Fetch product list from service
     */
    @GET(".")
    fun fetchProductList() : Single<Response<MutableList<ProductItem>>>
}
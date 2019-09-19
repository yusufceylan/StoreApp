package tr.com.storeapp.network.repository

import io.reactivex.Single
import retrofit2.Response
import tr.com.storeapp.R
import tr.com.storeapp.network.core.ApiException
import tr.com.storeapp.network.ApiService
import tr.com.storeapp.network.data.ProductItem
import tr.com.storeapp.utils.AppHelper

/**
 * Helper class that filter request and catch errors
 */
class ApiHelper constructor(private val apiService: ApiService, private val appHelper: AppHelper) : ApiRepository {


    override fun fetchProductList(params: HashMap<String, Any>?): Single<MutableList<ProductItem>> {
        return apiService.fetchProductList().flatMap { this.interceptError(it) }
    }

    /**
     * Filter service errors with
     */
    private fun <T> interceptError(response: Response<T>): Single<T?> {

        val requestCode = response.code()

        if (requestCode == 401) {
            return Single.never()
        } else if (requestCode in 400..501) {
            return Single.error(Throwable(appHelper.getContext().getString(R.string.api_exception_technical_error)))
        }

        return if (requestCode == 200 && response.isSuccessful) {

            if (response.body() == null) {
                Single.error(
                    ApiException(
                        appHelper.getContext().getString(R.string.api_exception_unknown),
                        "",
                        200
                    )
                )
            } else {
                Single.just(response.body())
            }
        } else Single.just(response.body())
    }
}
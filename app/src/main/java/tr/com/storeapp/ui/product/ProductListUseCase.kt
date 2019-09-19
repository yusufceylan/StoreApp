package tr.com.storeapp.ui.product

import io.reactivex.Single
import tr.com.storeapp.network.core.UseCase
import tr.com.storeapp.network.data.ProductItem
import tr.com.storeapp.network.repository.ApiRepositoryImp
import javax.inject.Inject

/**
 * Use Case class for fetching product list from service
 */
class ProductListUseCase @Inject constructor(private val apiImp : ApiRepositoryImp): UseCase<MutableList<ProductItem>, HashMap<String, Any>>() {

    override fun buildUseCaseObservable(params: HashMap<String, Any>?): Single<MutableList<ProductItem>> {
        return apiImp.fetchProductList(params)
    }
}
package tr.com.storeapp.ui.product

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import tr.com.storeapp.base.BaseViewModel
import tr.com.storeapp.network.core.ApiException
import tr.com.storeapp.network.data.ProductItem
import tr.com.storeapp.utils.NetworkState
import javax.inject.Inject

class ProductListActivityViewModel @Inject constructor(private val compositeDisposable: CompositeDisposable,
                                                       private val productListUseCase: ProductListUseCase) :
    BaseViewModel(compositeDisposable) {

    init {
        Timber.d("$this is ready")
        fetchProductList()
    }

    /**
     * Fetch product list from service
     */
    fun fetchProductList() {

        setNetworkStatus(NetworkState.LOADING)

        val disposable = productListUseCase.execute(object : DisposableSingleObserver<MutableList<ProductItem>>() {
            override fun onSuccess(t: MutableList<ProductItem>) {

                setNetworkStatus(NetworkState.LOADED)
                Timber.d("Result --> $t")
            }

            override fun onError(e: Throwable) {

                setNetworkStatus(NetworkState.FAILED)
                e.printStackTrace()
                if (e is ApiException) setError(e)

            }

        }, null)

        compositeDisposable.add(disposable)

    }
}
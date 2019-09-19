package tr.com.storeapp.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private val productList : MutableLiveData<MutableList<ProductItem>> = MutableLiveData()

    init {
        Timber.d("$this is ready")
        fetchProductList()
    }

    /**
     * Fetch product list from service
     */
    private fun fetchProductList() {

        setNetworkStatus(NetworkState.LOADING)

        val disposable = productListUseCase.execute(object : DisposableSingleObserver<MutableList<ProductItem>>() {
            override fun onSuccess(t: MutableList<ProductItem>) {

                setNetworkStatus(NetworkState.LOADED)
                Timber.d("Result --> $t")
                productList.value = t
            }

            override fun onError(e: Throwable) {

                setNetworkStatus(NetworkState.FAILED)
                e.printStackTrace()
                if (e is ApiException) setError(e)

            }

        }, null)

        compositeDisposable.add(disposable)

    }

    /**
     * Returns product list as live data
     */
    fun getProductList() : LiveData<MutableList<ProductItem>> {
        return productList
    }
}
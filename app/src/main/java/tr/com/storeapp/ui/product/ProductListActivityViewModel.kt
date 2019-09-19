package tr.com.storeapp.ui.product

import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import tr.com.storeapp.base.BaseViewModel
import javax.inject.Inject

class ProductListActivityViewModel @Inject constructor(private val compositeDisposable: CompositeDisposable) :
    BaseViewModel(compositeDisposable) {

    init {
        Timber.d("$this is ready")
    }
}
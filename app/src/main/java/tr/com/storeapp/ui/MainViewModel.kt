package tr.com.storeapp.ui

import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import tr.com.storeapp.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val compositeDisposable: CompositeDisposable) :
    BaseViewModel(compositeDisposable) {

    init {
        Timber.d("$this is ready")
    }
}
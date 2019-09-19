package tr.com.storeapp.ui

import android.os.Bundle
import timber.log.Timber
import tr.com.storeapp.BR
import tr.com.storeapp.R
import tr.com.storeapp.base.BaseActivity
import tr.com.storeapp.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        Timber.d("$this prepareView")
    }
}

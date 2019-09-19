package tr.com.storeapp.ui.product

import android.os.Bundle
import timber.log.Timber
import tr.com.storeapp.BR
import tr.com.storeapp.R
import tr.com.storeapp.base.BaseActivity
import tr.com.storeapp.databinding.ActivityProductListBinding
import kotlin.reflect.KClass

class ProductListActivity : BaseActivity<ActivityProductListBinding, ProductListActivityViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_product_list
    }

    override fun getViewModelClass(): KClass<ProductListActivityViewModel> {
        return ProductListActivityViewModel::class
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        Timber.d("$this prepareView")
    }
}

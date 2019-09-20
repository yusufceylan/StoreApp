package tr.com.storeapp.ui.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_product_list.*
import timber.log.Timber
import tr.com.storeapp.BR
import tr.com.storeapp.R
import tr.com.storeapp.adapter.ProductAdapter
import tr.com.storeapp.base.BaseActivity
import tr.com.storeapp.databinding.ActivityProductListBinding
import tr.com.storeapp.ui.MainActivity
import tr.com.storeapp.utils.NetworkState
import tr.com.storeapp.utils.extension.*
import kotlin.reflect.KClass

class ProductListActivity : BaseActivity<ActivityProductListBinding, ProductListActivityViewModel>() {

    private lateinit var mAdapter : ProductAdapter

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

        initAdapter()
        initObservers()
    }

    /**
     * Initialize adapters
     */
    private fun initAdapter() {
        rvProduct?.let {
            mAdapter = ProductAdapter(mutableListOf())
            it.adapter = mAdapter
        }
    }

    /**
     * Initialize the observers
     */
    private fun initObservers() {

        getViewModel().getNetworkStatus()?.observe(this, Observer {
            when(it.status) {
                NetworkState.Status.RUNNING -> { showProgressDialog() }
                NetworkState.Status.SUCCESS -> { hideProgressDialog() }
                NetworkState.Status.FAILED -> { hideProgressDialog()}
            }
        })

        getViewModel().getError()?.observe(this, Observer {
            showAlertDialog {
                setMessage(it.message)
                positiveButton(text = getString(R.string.okay))
            }
        })

        getViewModel().getProductList().observe(this, Observer {
            mAdapter.addData(it)
        })
    }


    /**
     * Runs when View elements clicked
     */
    fun onButtonClick(view : View?) {
        when(view?.id) {
            R.id.btnOrders -> { }
            R.id.btnLogout -> {
                showAlertDialog {
                    setMessage(getString(R.string.exit_message))
                    positiveButton(getString(R.string.yes)) { logoutUser() }
                    negativeButton(getString(R.string.no))
                }
            }
        }
    }

    /**
     * Logout user
     * Remove remember me status and go to login screen
     */
    private fun logoutUser() {
        getViewModel().appHelper.setRememberStatus(false)
        startActivityModal(Intent(this, MainActivity::class.java))
        finish()
    }

    /**
     * Override finish for finishing animation
     */
    override fun finish() {
        super.finish()
        slideOutModal()
    }
}

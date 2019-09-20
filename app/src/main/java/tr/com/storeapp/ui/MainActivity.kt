package tr.com.storeapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import tr.com.storeapp.BR
import tr.com.storeapp.R
import tr.com.storeapp.base.BaseActivity
import tr.com.storeapp.databinding.ActivityMainBinding
import tr.com.storeapp.ui.product.ProductListActivity
import tr.com.storeapp.utils.extension.positiveButton
import tr.com.storeapp.utils.extension.showAlertDialog
import tr.com.storeapp.utils.extension.startActivityFromRight
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

        if (isRememberMeOk()) {
            startActivityFromRight(Intent(this, ProductListActivity::class.java))
            finish()
        }

    }

    /**
     * Validate fields
     * [username] -> User name field
     * [password] -> User password field
     */
    private fun checkFields(username: String, password: String): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            showAlertDialog {
                setMessage(getString(R.string.error_empty_fields))
                positiveButton(getString(R.string.okay))
            }
            return false
        } else if (username != "kariyer" || password != "2019ADev") {
            showAlertDialog {
                setMessage(getString(R.string.error_validation))
                positiveButton(getString(R.string.okay))
            }
            return false
        }
        return true
    }

    /**
     * Runs when View elements clicked
     */
    fun onButtonClick(view : View?) {
        when(view?.id) {
            R.id.btnLogin -> {

                val username = tieUsername.text.toString().trim()
                val password = tiePassword.text.toString().trim()

                if (checkFields(username, password)) {
                    getViewModel().appHelper.setRememberStatus(scRemember.isChecked)
                    startActivityFromRight(Intent(this, ProductListActivity::class.java))
                    finish()
                }
            }
        }
    }

    /**
     * Check remember me status
     */
    private fun isRememberMeOk() : Boolean {
        return getViewModel().appHelper.getRememberStatus()
    }
}

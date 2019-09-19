package tr.com.storeapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import tr.com.storeapp.BR
import tr.com.storeapp.R
import tr.com.storeapp.base.BaseActivity
import tr.com.storeapp.databinding.ActivityMainBinding
import tr.com.storeapp.ui.product.ProductListActivity
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
            Toast.makeText(applicationContext, R.string.error_empty_fields, Toast.LENGTH_SHORT).show()
            return false
        } else if (username != "kariyer" || password != "2019ADev") {
            Toast.makeText(applicationContext, R.string.error_validation, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    /**
     * Runs when View elements clicked
     */
    fun onButtonClick(view : View?) {
        when(view?.id) {
            R.id.btn_login -> {

                val username = field_username.text.toString().trim()
                val password = field_password.text.toString().trim()

                if (checkFields(username, password)) {
                    getViewModel().appHelper.setRememberStatus(switch_remember.isChecked)
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

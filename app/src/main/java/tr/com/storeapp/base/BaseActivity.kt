package tr.com.storeapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import tr.com.storeapp.R
import tr.com.storeapp.utils.CustomDialog
import tr.com.storeapp.vm.ViewModelFactory
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Base Class for Activity classes
 */
abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : DaggerAppCompatActivity() {

    private lateinit var mCustomDialog: CustomDialog
    private lateinit var mViewDataBinding: DB
    private lateinit var mViewModel : VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set Custom Dialog
        mCustomDialog = CustomDialog(this, R.style.LoadingDialogStyle)
        // Set ViewModel
        mViewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass().java)
        // Set DataBinding
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
        // Initialize UI
        prepareView(savedInstanceState)
    }

    /**
     * Layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Returns ViewModel class type
     */
    protected abstract fun getViewModelClass(): KClass<VM>

    /**
     * Attach variable -> (ViewModel) to Layout
     */
    abstract fun getBindingVariable(): Int

    /**
     * Returns Current ViewModel Instance
     */
    fun getViewModel(): VM {
        return mViewModel
    }

    /**
     * Returns Current DataBinding Instance
     */
    fun getViewDataBinding() : DB {
        return mViewDataBinding
    }

    /**
     * Prepare UI Components here
     */
    abstract fun prepareView(savedInstanceState: Bundle?)

    /**
     * Show Progress Dialog
     */
    fun showProgressDialog() {

        try {
            if (!mCustomDialog.isShowing && !isFinishing) {
                mCustomDialog.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Hide Progress Dialog
     */
    fun hideProgressDialog() {

        try {
            if (mCustomDialog.isShowing)
                mCustomDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
package tr.com.storeapp.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base Holder Class for [RecyclerView.ViewHolder]
 */
abstract class BaseHolder<M, DB : ViewDataBinding> constructor(private val viewDataBinding : DB) : RecyclerView.ViewHolder(viewDataBinding.root) {

    /**
     * Getter for [M] class
     */
    var item: M? = null

    /**
     * Variable id that goes to layout
     */
    abstract fun bindingVariable(): Int

    /**
     * Set data to layout
     * @param data -> Model object
     */
    fun doBindings(data: M) {
        viewDataBinding.setVariable(bindingVariable(), data)
        viewDataBinding.executePendingBindings()
        this.item = data
    }

    /**
     * Binds holder data
     */
    abstract fun bind()

    /**
     * Getter for Row Class item [M]
     */
    fun getRowItem() : M? {
        return item
    }

    fun getRowBinding() : DB? {
        return viewDataBinding
    }
}
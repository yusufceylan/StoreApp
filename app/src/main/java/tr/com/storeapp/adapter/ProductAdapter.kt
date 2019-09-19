package tr.com.storeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import timber.log.Timber
import tr.com.storeapp.BR
import tr.com.storeapp.R
import tr.com.storeapp.base.BaseAdapter
import tr.com.storeapp.base.BaseHolder
import tr.com.storeapp.databinding.RowProductBinding
import tr.com.storeapp.network.data.ProductItem
import tr.com.storeapp.utils.extension.convertMonthName
import tr.com.storeapp.utils.extension.currencyFormat
import tr.com.storeapp.utils.extension.setGone
import tr.com.storeapp.utils.extension.setVisible

/**
 * Adapter for listing products
 */
class ProductAdapter constructor(private val mData : MutableList<ProductItem>) :
    BaseAdapter<ProductItem, RowProductBinding, ProductAdapter.ProductVH>(mData) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        return ProductVH(RowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    inner class ProductVH constructor(rowProductBinding: RowProductBinding): BaseHolder<ProductItem, RowProductBinding>(rowProductBinding) {

        override fun bindingVariable(): Int {
            return BR.data
        }

        override fun bind() {
            Timber.d(getRowItem().toString())

            getRowItem()?.date?.let { getRowBinding()?.tvDate?.text = it }
            getRowItem()?.month?.let { getRowBinding()?.tvMonth?.text = convertMonthName(it.toInt()) }
            getRowItem()?.marketName?.let { getRowBinding()?.tvMarketName?.text = it }
            getRowItem()?.orderName?.let { getRowBinding()?.tvProductName?.text = it }
            getRowItem()?.productPrice?.let { getRowBinding()?.tvProductPrice?.text = it.currencyFormat() }
            getRowItem()?.productState?.let {
                when(it) {
                    ProductItem.CONFIRMED -> {
                        getRowBinding()?.ivProductState?.setImageResource(R.drawable.bg_confirmed)
                        getRowBinding()?.tvProductState?.setTextColor(ContextCompat.getColor(getRowBinding()?.root?.context!!, android.R.color.holo_green_dark))
                    }
                    ProductItem.WAITING -> {
                        getRowBinding()?.ivProductState?.setImageResource(R.drawable.bg_waiting)
                        getRowBinding()?.tvProductState?.setTextColor(ContextCompat.getColor(getRowBinding()?.root?.context!!, android.R.color.holo_red_dark))
                    }
                    ProductItem.PREPARING -> {
                        getRowBinding()?.ivProductState?.setImageResource(R.drawable.bg_preparing)
                        getRowBinding()?.tvProductState?.setTextColor(ContextCompat.getColor(getRowBinding()?.root?.context!!, android.R.color.holo_orange_dark))
                    }
                    else -> {}
                }
                getRowBinding()?.tvProductState?.text = it
            }

            getRowItem()?.productDetail?.orderDetail?.let { getRowBinding()?.tvProductDetailName?.text = it }
            getRowItem()?.productDetail?.summaryPrice?.let { getRowBinding()?.tvProductDetailPrice?.text = it.currencyFormat() }

            // Check Detail open or not
            getRowItem()?.isSelected?.let {
                if (it)
                    getRowBinding()?.llProductDetailWrapper?.setVisible()
                else
                    getRowBinding()?.llProductDetailWrapper?.setGone()
            }

            // Set Click Listener
            getRowBinding()?.llRoot?.setOnClickListener {
                getRowItem()?.let { changeSelection(it) }
                notifyItemChanged(adapterPosition)
            }
        }
    }


    /**
     * Change the selected state of clicked Item
     */
    private fun changeSelection(item : ProductItem) {
        item.isSelected = !item.isSelected
    }
}
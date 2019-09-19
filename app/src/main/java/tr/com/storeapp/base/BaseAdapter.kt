package tr.com.storeapp.base

import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base Class for [RecyclerView.Adapter]
 */
abstract class BaseAdapter<M, DB : ViewDataBinding, VH : BaseHolder<M, DB>> constructor(private var mData: MutableList<M>?) :
    RecyclerView.Adapter<VH>() {

    override fun onBindViewHolder(@NonNull holder: VH, position: Int) {
        val data = mData!![position]

        if (data != null) {
            holder.doBindings(data)
            holder.bind()
        }
    }

    fun addData(newData: List<M>?) {
        if (newData == null)
            return

        if (mData == null)
            mData = ArrayList()
        else
            mData!!.clear()

        mData!!.addAll(newData)
        this.notifyDataSetChanged()
    }

    fun addData(data: M?, position: Int) {

        if (data == null)
            return

        if (mData == null)
            mData = ArrayList()

        mData!!.add(position, data)
        notifyItemInserted(position)
    }

    fun removeData(position: Int) {
        mData!!.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {

        return if (mData == null) 0 else mData!!.size

    }

    fun getData(): MutableList<M>? {
        return mData
    }
}
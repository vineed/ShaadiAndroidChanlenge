/*
package com.shaadi.shaadiandroidchallenge.core.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.RecyclerView
import com.ftl.mfocus.BR
import com.ftl.mfocus.R
import com.ftl.mfocus.common.base.BaseViewModel

open class GenericAdapter<T>(
    context: Context,
    private val layoutId: Int,
    var listItems: MutableList<T> = mutableListOf(),
    var config: Map<String, Any>? = null
) :
    RecyclerView.Adapter<DataViewHolder<T>>() {

    protected var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var onItemClickListener: OnItemClickListener<T>? = null

    var onMultiItemClickListener: OnMultiItemClickListener<T>? = null

    var viewModel: BaseViewModel? = null
    var view: Any? = null

init {

if (liveListItems != null) {
            val liveItems = liveListItems.value

            if (liveItems != null) {
                listItems = liveItems
            }
        }


if (context is LifecycleOwner) {
            liveListItems?.observe(context, Observer {
                if (listItems == it)
                    notifyDataSetChanged()
                else {
                    setItems(it)
                }
            })
        }
    }


    fun setItems(listItems: MutableList<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    fun addItem(item: T) {
        this.listItems.add(item)
        notifyDataSetChanged()
    }

    fun appendItems(items: Collection<T>) {
        this.listItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder<T> {
        return DataViewHolder(layoutInflater.inflate(layoutId, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder<T>, position: Int) {
        //(holder as Binder<T>).bind(listItems[position])

        val item = listItems[position]

        if (onMultiItemClickListener == null) {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClicked(position, it, item)
            }
        } else {
            onMultiItemClickListener?.let {
                it.ids.forEach { id ->
                    val view = holder.itemView.findViewById<View>(id)

                    view.setOnClickListener {
                        onMultiItemClickListener?.onItemClicked(position, id, item)
                    }
                }
            }
        }

        holder.bindData(item, viewModel, config, view, position)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    //override fun getItemViewType(position: Int): Int = layoutId

    //protected abstract fun getLayoutId(position: Int, obj: T): Int

    //abstract fun getViewHolder(view: View): DataViewHolder<T>

    //abstract fun bind(holder: DataViewHolder, position: Int)
}

open class DataViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemViewBinder: ViewDataBinding? = null

    init {
        itemViewBinder = DataBindingUtil.bind(itemView)
    }

    open fun bindData(
        item: T,
        viewModel: BaseViewModel?,
        config: Map<String, Any>?,
        view: Any?,
        position: Int
    ) {
        itemViewBinder?.setVariable(BR.data, item)
        itemViewBinder?.setVariable(BR.pos, position)

        if (viewModel != null) itemViewBinder?.setVariable(BR.vm, viewModel)
        if (view != null) itemViewBinder?.setVariable(BR.view, view)

        if (config != null) itemViewBinder?.setVariable(BR.config, config)

        itemViewBinder?.executePendingBindings()
    }

    fun unbindView() {
        itemViewBinder?.unbind()
    }
}

interface OnItemClickListener<T> {
    fun onItemClicked(position: Int, view: View, data: T)
}

interface OnMultiItemClickListener<T> {
    var ids: IntArray

    fun onItemClicked(position: Int, which: Int, data: T)
}
*/

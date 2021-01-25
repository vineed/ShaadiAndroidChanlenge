package com.shaadi.shaadiandroidchallenge.utils

import androidx.recyclerview.widget.DiffUtil
import timber.log.Timber

class GenericDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val areItemTheSameAction: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsTheSameAction: ((oldItem: T, newItem: T) -> Boolean)? = null,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemTheSameAction(
            oldList[oldItemPosition],
            newList[newItemPosition]
        )
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val mAreContentsTheSameAction = areContentsTheSameAction

        return if (mAreContentsTheSameAction != null) {
            mAreContentsTheSameAction(
                oldList[oldItemPosition],
                newList[newItemPosition]
            )
        } else {
            oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]


        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}
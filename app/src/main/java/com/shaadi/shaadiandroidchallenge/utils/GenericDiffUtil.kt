package com.shaadi.shaadiandroidchallenge.utils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class GenericDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val areItemTheSameAction: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsTheSameAction: ((oldItem: T, newItem: T) -> Boolean)? = null,
    private val payloadAction: ((oldItem: T, newItem: T) -> Bundle)? = null
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
        return areContentsTheSameAction
            ?.let {
                it(
                    oldList[oldItemPosition],
                    newList[newItemPosition]
                )
            } ?: oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) =
        payloadAction
            ?.let {
                it(oldList[oldItemPosition], newList[newItemPosition])
            } ?: super.getChangePayload(oldItemPosition, newItemPosition)
}
package com.application.domain.extensions

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

fun <T> RecyclerView.Adapter<*>.autoNotify(
    oldList: List<T>,
    newList: List<T>,
    compare: (T, T) -> Boolean
) {
    val diffUtil = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            compare(oldList.elementAt(oldItemPosition), newList.elementAt(newItemPosition))

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList.elementAt(oldItemPosition) == newList.elementAt(newItemPosition)

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    })
    diffUtil.dispatchUpdatesTo(this)
}
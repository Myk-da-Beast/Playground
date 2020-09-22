package com.myk.library.base.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Base Recycler View Adapter to be used with a list of some Domain Model.
 *
 * @param T: Type of item to be used with this recycler view
 * @param VB: ViewBinding for the view holder to use.
 * @param VH: View Holder class to be used with this adapter. Must subclass [BaseAdapter.ViewHolder]
 * @param items: List of items to be used with this adapter
 */
abstract class BaseAdapter<T, VB : ViewBinding, VH : BaseAdapter.ViewHolder<T, VB>>(
    private var items: List<T>
) : RecyclerView.Adapter<VH>() {

    private val onItemClickListener: ((T) -> Unit)? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items.getOrNull(position) ?: return
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<T>) {
        this.items = items
        this.notifyDataSetChanged()
    }

    abstract class ViewHolder<T, VB : ViewBinding>(
        open val binding: VB
    ) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T?)
    }
}

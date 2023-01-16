package com.sberger.square_employeedirectory.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// A RecyclerView that supports showing a view as an empty state
// Based on gist from https://gist.github.com/lukaville/b372bfea8168dfdd2903
class RecyclerViewWithEmpty(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    private var emptyView: View? = null
    private val observer = AdapterDataObserverWithEmpty(this)

    fun checkIfEmpty() {
        if (emptyView != null && adapter != null) {
            val emptyViewVisible = adapter!!.itemCount == 0
            emptyView!!.visibility = if (emptyViewVisible) VISIBLE else GONE
            visibility = if (emptyViewVisible) GONE else VISIBLE
        }
    }

    override fun setAdapter(newAdapter: Adapter<*>?) {
        adapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(newAdapter)
        newAdapter?.registerAdapterDataObserver(observer)

        checkIfEmpty()
    }

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
        checkIfEmpty()
    }
}

class AdapterDataObserverWithEmpty(private var recyclerView: RecyclerViewWithEmpty?) : RecyclerView.AdapterDataObserver() {
    override fun onChanged() {
        recyclerView?.checkIfEmpty()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        recyclerView?.checkIfEmpty()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        recyclerView?.checkIfEmpty()
    }
}
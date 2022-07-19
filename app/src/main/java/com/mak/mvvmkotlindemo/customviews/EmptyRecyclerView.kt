package com.mak.mvvmkotlindemo.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver

/**
 * This class is custom RecyclerView. Which contains a view that display when record not found.
 * <p>
 * Copyright (c) 2018 <ClientName>. All rights reserved.
 * Created by mak on 21/9/18.
</ClientName> */
class EmptyRecyclerView : RecyclerView {

    private var emptyView: View? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
    }

    fun checkIfEmpty() {
        if (emptyView != null && adapter != null) {
            emptyView!!.visibility =
                if (adapter!!.itemCount > 0) GONE else VISIBLE
        }
    }

    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)
        checkIfEmpty()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (null != emptyView && (visibility == GONE || visibility == INVISIBLE)) {
            emptyView!!.visibility = GONE
        } else {
            checkIfEmpty()
        }
    }

    fun setEmptyView(emptyView: View?) {
        this.emptyView = emptyView
        checkIfEmpty()
    }
}
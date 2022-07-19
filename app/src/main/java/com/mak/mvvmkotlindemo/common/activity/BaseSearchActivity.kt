package com.mak.mvvmkotlindemo.common.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mak.mvvmkotlindemo.R
import com.mak.mvvmkotlindemo.common.CommonConstants
import com.mak.mvvmkotlindemo.common.adapter.CommonAdaptor
import com.mak.mvvmkotlindemo.common.models.CommonModel
import com.mak.mvvmkotlindemo.customviews.EmptyRecyclerView
import com.mak.mvvmkotlindemo.databinding.ActivityBaseSearchBinding
import com.mak.mvvmkotlindemo.utils.CollectionUtils

/**
 * This class
 *
 *
 * Copyright (c) 2018 <ClientName>. All rights reserved.
 * Created by mak on 17/10/18.
</ClientName> */
abstract class BaseSearchActivity : BaseActivity<ActivityBaseSearchBinding?>(),
    CommonAdaptor.OnItemClickListener {
    private var mRecyclerView: EmptyRecyclerView? = null
    private var mEmptyView: TextView? = null
    private var mCommonAdaptor: CommonAdaptor? = null
    private val mCommonModels: MutableList<CommonModel> = ArrayList()
    abstract fun setActionBarTitle(): String?
    abstract fun setActionMenuClickListener(): ActionMenuClickListener?
    var mActionMenuClickListener: ActionMenuClickListener? = null
    private var menuItemAddVisible = true

    override val isFullScreen: Boolean
        get() = false

    override val viewBinding: ActivityBaseSearchBinding?
        get() = ActivityBaseSearchBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding!!.toolbar.toolbar)
        setAppActionBar()
        setUpTitle()
        mActionMenuClickListener = setActionMenuClickListener()
        mRecyclerView = binding!!.rvCommon.recycleView
        mEmptyView = binding!!.rvCommon.emptyView
        initRecyclerView()
        binding!!.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mCommonAdaptor!!.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
        setRecyclerAdapter(mRecyclerView!!)
    }

    private fun setAppActionBar() {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        val drawable = binding!!.toolbar.toolbar.navigationIcon
        drawable?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorActionbarIcons),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    /**
     * setting here title
     */
    private fun setUpTitle() {
        if (supportActionBar != null) {
            supportActionBar!!.title = null
            binding!!.toolbar.tvTitle.text = setActionBarTitle()
        }
    }

    private fun initRecyclerView() {
        mRecyclerView!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRecyclerView!!.setEmptyView(mEmptyView)
    }

    private fun setRecyclerAdapter(recyclerView: RecyclerView) {
        mCommonAdaptor = CommonAdaptor(this, mCommonModels, this)
        recyclerView.adapter = mCommonAdaptor
    }

    protected fun addItems(list: List<CommonModel>) {
        if (CollectionUtils.isNullOrEmpty(list)) {
            mEmptyView!!.visibility = View.VISIBLE
            mEmptyView!!.text = getString(R.string.empty_list_message)
        } else {
            mEmptyView!!.visibility = View.GONE
        }
        mCommonModels.clear()
        mCommonModels.addAll(list)
        mCommonAdaptor!!.addAll(mCommonModels)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

//        getMenuInflater().inflate(R.menu.menu_add, menu);
//        MenuItem menuItemAdd = menu.findItem(R.id.action_add);
//        menuItemAdd.setVisible(menuItemAddVisible);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun setTotal(resID: Int, count: Int) {
        val res = resources
        val text = res.getQuantityString(resID, count, count)
        val wordToSpan: Spannable = SpannableString(text)
        wordToSpan.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    this,
                    R.color.syncCounterTextColor
                )
            ), 0, text.indexOf(CommonConstants.SEPARATOR_SPACE), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding!!.tvTotal.text = wordToSpan
    }

    protected fun setMenuItemAddVisibility(isVisible: Boolean) {
        menuItemAddVisible = isVisible
        invalidateOptionsMenu()
    }

    interface ActionMenuClickListener {
        fun onActionMenuClickListener(itemId: Int)
    }
}
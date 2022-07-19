package com.mak.mvvmkotlindemo.common.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mak.mvvmkotlindemo.R
import com.mak.mvvmkotlindemo.common.CommonConstants
import com.mak.mvvmkotlindemo.common.models.CommonModel
import com.mak.mvvmkotlindemo.databinding.ItemCommonListFragmentBinding
import java.util.*

/**
 * This class is common adaptor. which used in [.common.activity.BaseSearchActivity]
 *
 *
 * Copyright (c) 2018 <ClientName>. All rights reserved.
 * Created by mak on 27/9/18.
</ClientName> */
class CommonAdaptor(
    private val mContext: Context,
    items: MutableList<CommonModel>?,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CommonAdaptor.ViewHolder>() {
    private val mItems: MutableList<CommonModel>
    private val mListOrigin: MutableList<CommonModel>
    private val mOnItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCommonListFragmentBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mItems[position]
        val name = model.title
        val subTitle = model.subTitle
        val imageLinks = model.imageLinks
        var imageUrl: String? = null
        if (imageLinks != null) {
            imageUrl = imageLinks.smallThumbnail
        }
        holder.binding.heading.text = name
        holder.binding.subHeading.text = subTitle
        holder.binding.progressBar.visibility = View.GONE
        //        Picasso.with(mContext).cancelRequest(holder.binding.image);
        if (imageUrl == null) {
            holder.binding.imageRoot.visibility = View.GONE
        } else {
            holder.binding.imageRoot.visibility = View.VISIBLE
            if (!TextUtils.isEmpty(imageUrl)) {
                if (imageUrl.startsWith(CommonConstants.EXTRA_IMAGE_STARTS_HTTP)) {
                    Glide.with(mContext)
                        .load(imageUrl)
                        .centerCrop()
                        .fitCenter()
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image)
                        .into(holder.binding.image)

//                            .into(holder.binding.image, new Callback() {
//                                @Override
//                                public void onSuccess() {
//                                    if (holder.binding.progressBar != null) {
//                                        holder.binding.progressBar.setVisibility(View.GONE);
//                                    }
//                                }
//
//                                @Override
//                                public void onError() {
//                                    if (holder.binding.progressBar != null) {
//                                        holder.binding.progressBar.setVisibility(View.GONE);
//                                    }
//                                }
//                            });
                } else {
                    holder.binding.image.setImageDrawable(
                        ContextCompat.getDrawable(
                            mContext.applicationContext,
                            R.drawable.placeholder_image
                        )
                    )
                }
            } else {
                holder.binding.image.setImageDrawable(
                    ContextCompat.getDrawable(
                        mContext.applicationContext,
                        R.drawable.placeholder_image
                    )
                )
            }
        }
        holder.itemView.setOnClickListener { v: View? ->
            mOnItemClickListener.onItemClick(
                holder.adapterPosition,
                model
            )
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    /**
     * Filter Adapter based on user search request.
     *
     * @param charText search value
     */
    fun filter(charText: String) {
        mItems.clear()
        if (TextUtils.isEmpty(charText)) {
            mItems.addAll(mListOrigin)
        } else {
            for (contact in mListOrigin) {
                val charTextLower = charText.lowercase(Locale.getDefault())
                if (contact.title?.lowercase(Locale.getDefault())?.contains(charTextLower) == true) {
                    mItems.add(contact)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun addAll(commonModels: List<CommonModel>?) {
        mItems.clear()
        mListOrigin.clear()
        mItems.addAll(commonModels!!)
        mListOrigin.addAll(commonModels)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemCommonListFragmentBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )

    interface OnItemClickListener {
        fun onItemClick(position: Int, model: CommonModel?)
    }

    init {
        mItems = ArrayList()
        mListOrigin = ArrayList()
        mItems.addAll(items!!)
        mListOrigin.addAll(items)
        mOnItemClickListener = onItemClickListener
    }
}
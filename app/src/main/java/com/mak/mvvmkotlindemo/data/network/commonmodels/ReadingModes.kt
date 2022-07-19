package com.mak.mvvmkotlindemo.data.network.commonmodels

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.mak.mvvmkotlindemo.data.network.commonmodels.VolumeInfo
import com.mak.mvvmkotlindemo.data.network.commonmodels.SaleInfo
import com.mak.mvvmkotlindemo.data.network.commonmodels.AccessInfo
import com.mak.mvvmkotlindemo.data.network.commonmodels.ListPrice_
import com.mak.mvvmkotlindemo.data.network.commonmodels.RetailPrice_
import com.mak.mvvmkotlindemo.data.network.commonmodels.ListPrice
import com.mak.mvvmkotlindemo.data.network.commonmodels.RetailPrice
import com.mak.mvvmkotlindemo.data.network.commonmodels.Offer
import com.mak.mvvmkotlindemo.data.network.commonmodels.Epub
import com.mak.mvvmkotlindemo.data.network.commonmodels.Pdf
import com.mak.mvvmkotlindemo.data.network.commonmodels.IndustryIdentifier
import com.mak.mvvmkotlindemo.data.network.commonmodels.ReadingModes
import com.mak.mvvmkotlindemo.data.network.commonmodels.ImageLinks
import com.mak.mvvmkotlindemo.data.network.commonmodels.PanelizationSummary

/**
 *
 *
 * Copyright (c) 2022 <ClientName>. All rights reserved.
 * Created by mohammadarshikhan on 2022-05-07.
 */
class ReadingModes {
    @SerializedName("text")
    @Expose
    var text: Boolean? = null

    @SerializedName("image")
    @Expose
    var image: Boolean? = null
}
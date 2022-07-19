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
class VolumeInfo {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("authors")
    @Expose
    var authors: List<String>? = null

    @SerializedName("publisher")
    @Expose
    var publisher: String? = null

    @SerializedName("publishedDate")
    @Expose
    var publishedDate: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("industryIdentifiers")
    @Expose
    var industryIdentifiers: List<IndustryIdentifier>? = null

    @SerializedName("readingModes")
    @Expose
    var readingModes: ReadingModes? = null

    @SerializedName("pageCount")
    @Expose
    var pageCount: Int? = null

    @SerializedName("printType")
    @Expose
    var printType: String? = null

    @SerializedName("categories")
    @Expose
    var categories: List<String>? = null

    @SerializedName("averageRating")
    @Expose
    var averageRating: Double? = null

    @SerializedName("ratingsCount")
    @Expose
    var ratingsCount: Int? = null

    @SerializedName("maturityRating")
    @Expose
    var maturityRating: String? = null

    @SerializedName("allowAnonLogging")
    @Expose
    var allowAnonLogging: Boolean? = null

    @SerializedName("contentVersion")
    @Expose
    var contentVersion: String? = null

    @SerializedName("imageLinks")
    @Expose
    var imageLinks: ImageLinks? = null

    @SerializedName("language")
    @Expose
    var language: String? = null

    @SerializedName("previewLink")
    @Expose
    var previewLink: String? = null

    @SerializedName("infoLink")
    @Expose
    var infoLink: String? = null

    @SerializedName("canonicalVolumeLink")
    @Expose
    var canonicalVolumeLink: String? = null

    @SerializedName("subtitle")
    @Expose
    var subtitle: String? = null

    @SerializedName("panelizationSummary")
    @Expose
    var panelizationSummary: PanelizationSummary? = null
}
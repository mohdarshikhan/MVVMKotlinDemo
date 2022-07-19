package com.mak.mvvmkotlindemo.data.network.responses

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.mak.mvvmkotlindemo.data.network.commonmodels.Item

class GoogleBooksVolumesResponse {
    @SerializedName("kind")
    @Expose
    var kind: String? = null

    @SerializedName("totalItems")
    @Expose
    var totalItems: Int? = null

    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
}
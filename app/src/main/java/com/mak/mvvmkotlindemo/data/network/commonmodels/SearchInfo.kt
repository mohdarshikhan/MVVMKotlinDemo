package com.mak.mvvmkotlindemo.data.network.commonmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 *
 * Copyright (c) 2022 <ClientName>. All rights reserved.
 * Created by mohammadarshikhan on 2022-05-07.
 */
class SearchInfo {
    @SerializedName("textSnippet")
    @Expose
    var textSnippet: String? = null
}
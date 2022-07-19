package com.mak.mvvmkotlindemo.data.network.commonmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 *
 * Copyright (c) 2022 <ClientName>. All rights reserved.
 * Created by mohammadarshikhan on 2022-05-07.
 */
class Epub {
    @SerializedName("isAvailable")
    @Expose
    var isAvailable: Boolean? = null

    @SerializedName("acsTokenLink")
    @Expose
    var acsTokenLink: String? = null
}
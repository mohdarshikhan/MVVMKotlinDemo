package com.mak.mvvmkotlindemo.data.network.commonmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 *
 * Copyright (c) 2022 <ClientName>. All rights reserved.
 * Created by mohammadarshikhan on 2022-05-07.
 */
class ImageLinks {
    @SerializedName("smallThumbnail")
    @Expose
    var smallThumbnail: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
}
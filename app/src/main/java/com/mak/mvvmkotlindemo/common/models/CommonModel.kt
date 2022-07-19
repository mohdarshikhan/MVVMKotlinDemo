package com.mak.mvvmkotlindemo.common.models

import com.mak.mvvmkotlindemo.data.network.commonmodels.ImageLinks

/**
 * This class is common model for list screen with search bar on top level
 *
 *
 * Copyright (c) 2018 <ClientName>. All rights reserved.
 * Created by mak on 26/9/18.
</ClientName> */
class CommonModel {
    var id: String? = null
    var title: String? = null
    var subTitle: String? = null
    var authors: List<String>? = null
    var publisher: String? = null
    var publishedDate: String? = null
    var description: String? = null
    var categories: String? = null
    var imageLinks: ImageLinks? = null
}
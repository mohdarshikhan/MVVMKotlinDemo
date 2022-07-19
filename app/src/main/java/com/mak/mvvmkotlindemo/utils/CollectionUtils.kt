package com.mak.mvvmkotlindemo.utils

/**
 * This class contains Collection related methods
 *
 *
 * Copyright (c) 2019 <ClientName>. All rights reserved.
 * Created by mak on 25/1/19.
</ClientName> */
object CollectionUtils {
    /**
     * Checking a collection is empty or null
     *
     * @param list Collection
     * @return true if collection empty or null else false
     */
    fun isNullOrEmpty(list: Collection<*>?): Boolean {
        return list == null || list.isEmpty()
    }
}
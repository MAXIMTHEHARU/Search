package com.android.imagesearch

data class SearchItemModel(
    var title: String,
    var dateTime: String,
    var url: String,
    var isLike: Boolean = false
)
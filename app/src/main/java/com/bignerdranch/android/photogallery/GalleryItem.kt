package com.bignerdranch.android.photogallery

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryItem(
    var id: String = "",
    var title: String = "",
//    @SerializedName("url_s") //gson
    @Json(name="url_s")
    var url: String = ""
)
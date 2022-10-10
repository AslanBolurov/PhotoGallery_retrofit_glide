package com.bignerdranch.android.photogallery.api

import com.bignerdranch.android.photogallery.GalleryItem
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PhotoResponse {

//    @SerializedName("photo") //gson
    @Json(name="photo")
    lateinit var galleryItems:List<GalleryItem>
}
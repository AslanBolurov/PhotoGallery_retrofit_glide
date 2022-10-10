package com.bignerdranch.android.photogallery.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FlickrResponse {
//    @Json(name="photos")
    lateinit var photos: PhotoResponse
}
package com.bignerdranch.android.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.FlickrResponse
import com.bignerdranch.android.photogallery.api.RetrofitInstance
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "FlickrFetchr"


class FlickrFetchr {


    suspend fun fetchPhotos():List<GalleryItem>{
       return RetrofitInstance.flickrApi.fetchPhotos().photos.galleryItems
    }



}
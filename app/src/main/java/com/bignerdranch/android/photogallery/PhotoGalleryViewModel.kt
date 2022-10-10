package com.bignerdranch.android.photogallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotoGalleryViewModel(
    private val repository: FlickrFetchr
) : ViewModel(){

    val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()

    init {
        viewModelScope.launch {
            execute()
        }
    }


    suspend fun execute(){
        responseLiveData.value=repository.fetchPhotos()
    }


}



package com.b

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.photogallery.FlickrFetchr
import com.bignerdranch.android.photogallery.GalleryItem
import com.bignerdranch.android.photogallery.PhotoGalleryViewModel
import com.bignerdranch.android.photogallery.databinding.FragmentPhotoGalleryBinding
import com.bumptech.glide.Glide
import java.lang.IllegalArgumentException

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment : Fragment() {

    private lateinit var photoRecyclerView: RecyclerView

    private var _binding: FragmentPhotoGalleryBinding? = null
    val binding get() = _binding!!

    private val photoGalleryViewModel: PhotoGalleryViewModel by viewModels{
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(PhotoGalleryViewModel::class.java)){
                    return PhotoGalleryViewModel(FlickrFetchr()) as T
                }else {
                    throw IllegalArgumentException("Unknown class name")
                }
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        photoRecyclerView = binding.photoRecyclerView
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoGalleryViewModel.responseLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItems ->
                photoRecyclerView.adapter=PhotoAdapter(galleryItems)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private lateinit var itemImageView: ImageView

    private class PhotoHolder(itemImageView: ImageView) : RecyclerView.ViewHolder(itemImageView) {

    }

    private class PhotoAdapter(
        private val galleryItems: List<GalleryItem>
    ) : RecyclerView.Adapter<PhotoHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
            val imageView=ImageView(parent.context)
            return PhotoHolder(imageView)
        }

        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val galleryItem=galleryItems[position]
            val itemImageView=holder.itemView

            Glide.with(itemImageView.context)
                .load(galleryItem.url)
                .into(itemImageView as ImageView)
        }

        override fun getItemCount(): Int=galleryItems.size

    }


    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }

}

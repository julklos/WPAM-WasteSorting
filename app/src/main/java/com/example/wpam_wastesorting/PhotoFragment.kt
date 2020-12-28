package com.example.wpam_wastesorting

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.photo_creator.view.*


class PhotoFragment: Fragment() {
    private val CAMERA_REQUEST: Int = 1
    private  val GALLERY_REQUEST = 9

    //Widgets
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.photo_creator, container, false)

//        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

//        Set onclick listener on ImageView
//
//        Set onclick listener on ImageView
//        view.image_view.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                //Open image gallery method
////                getImageFromGallery()
//                capturePictureFromCamera()
//
//            }
//        })
        capturePictureFromCamera()
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //called when image was captured from camera intent
        //super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            var bmp = data?.extras?.get("data") as Bitmap
            view?.image_view?.setImageBitmap(bmp)
        }
    }
    private fun capturePictureFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST)

    }


}


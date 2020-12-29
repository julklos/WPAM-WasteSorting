package com.example.wpam_wastesorting

import android.R.attr.bitmap
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ibm.watson.developer_cloud.service.security.IamOptions
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions
import kotlinx.android.synthetic.main.photo_creator.view.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*


class PhotoFragment: Fragment() {
    private var bmp: Bitmap? = null
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
        view.trash_button.setOnClickListener({
            bmp?.let { it1 -> getClassifierCategory(it1) }
        })
        capturePictureFromCamera()
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //called when image was captured from camera intent
        //super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
           bmp = data?.extras?.get("data") as Bitmap

//            getClassifierCategory(bmp)
           view?.image_category?.setText("BIO")
          view?.image_view?.setImageBitmap(bmp)
        }
    }

    private fun getClassifierCategory(bitmap: Bitmap) {
        val options = IamOptions.Builder()
            .apiKey("p-7651a70ec3ef89994d5181ac6061dbea4f788e4f")
            .build()
        println("options")
        val service = VisualRecognition("2018-03-19",options)
        service.endPoint= "https://api.eu-de.visual-recognition.watson.cloud.ibm.com"
        val bos = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos)
        val bitmapdata: ByteArray = bos.toByteArray()
        val bs = ByteArrayInputStream(bitmapdata)
        println("second part")
//        val imagesStream: InputStream = FileInputStream("./fruitbowl.jpg")
        val classifyOptions: ClassifyOptions = ClassifyOptions.Builder()
            .imagesFile(bs)
            .imagesFilename("photo.PNG")
            .threshold(0.6.toFloat())
            .classifierIds(Arrays.asList("DefaultCustomModel_199116508"))
            .build()
        println("build")
        val result: ClassifiedImages = service.classify(classifyOptions).execute()
        println(result)

    }

    private fun capturePictureFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST)

    }


}


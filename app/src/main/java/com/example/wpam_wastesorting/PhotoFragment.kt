package com.example.wpam_wastesorting

//import com.ibm.watson.developer_cloud.service.security.IamOptions
//import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition
//import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages
//import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.photo_creator.view.*
import java.io.ByteArrayOutputStream


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
        view.loading_spinner.bringToFront()
        view.loading_spinner.isVisible = true
        view?.good_react?.isVisible = false
        view?.bad_react?.isVisible = false

        view.good_react.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                emotionClicked()
            }
        })
        view.bad_react.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                emotionClicked()
            }
        })
        view.another_photo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                capturePictureFromCamera()
            }
        })
        capturePictureFromCamera()
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //called when image was captured from camera intent
        //super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
           bmp = data?.extras?.get("data") as Bitmap
            val image_base64 = this.encodeImage(bmp!!)
            ImageService.classify(image_base64!!){
                success, classificationResult ->
                view?.loading_spinner?.isVisible = false
                view?.image_view?.setImageBitmap(bmp)
                view?.good_react?.isVisible = true
                view?.bad_react?.isVisible = true
                this.setUp(classificationResult)
            }

            }

        }


    private fun capturePictureFromCamera() {
        view?.loading_spinner?.isVisible = true
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST)

    }
    private fun emotionClicked(){
        view?.good_react?.isVisible = false
        view?.bad_react?.isVisible = false
        Toast.makeText(activity ,"Answer added. Thank you.", Toast.LENGTH_LONG).show()
    }
    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
    private fun setUp(result: ClassificationResponse){
        val colorname = result.trash_class+"Trash"
        val classname = "trash_"+result.trash_class
        val description = classname+ "_description"
        view?.trash_answer_icon!!.setColorFilter(ContextCompat.getColor(this.requireContext(), resources.getIdentifier(colorname, "color", context?.packageName)));
        view?.trash_answer_name!!.setText(resources.getIdentifier(classname, "string",context?.packageName))
        view?.trash_answer_name!!.setTextColor(ContextCompat.getColor(this.requireContext(), resources.getIdentifier(colorname, "color", context?.packageName)))
        view?.trash_answer_donts!!.setText(resources.getIdentifier(description, "string",context?.packageName))
    }


}


package com.example.wpam_wastesorting

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.trash_answer.view.*

class TrashAnswerFragment: Fragment() {

    var id = "0"
    lateinit var trash_image : ImageView
    var base64_image = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//            super.onCreate(savedInstanceState)

        val view: View = inflater.inflate(R.layout.trash_answer, container, false)
        val file_name =   this.arguments?.getString("file_name").toString()
        this.id = this.arguments?.getString("file_id").toString()
        println(this.id)
        this.base64_image = this.arguments?.getString("image_base64").toString()
        this.trash_image = view.findViewById(R.id.trash_image_answer)
        val imageBytes = Base64.decode(base64_image, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        trash_image.setImageBitmap(decodedImage)

        view.trash_glass.setOnClickListener { v -> this.sendAnswer("glass") }
        view.trash_food.setOnClickListener{v-> this.sendAnswer("food")}
        view.trash_others.setOnClickListener{v-> this.sendAnswer("others")}
        view.trash_paper.setOnClickListener{v-> this.sendAnswer("paper")}
        view.trash_plastic.setOnClickListener{v->this.sendAnswer("plastic")}


//        view.trash_title?.setText(file_name)
        Log.d("trashTitle", this.arguments?.getString("file_name").toString())
    return view
        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
private fun sendAnswer(value: String){
    ImageService.answer(value, this.id){
            success, answerResult ->
        println(answerResult)
        Toast.makeText(activity ,"Answer added. Thank you.", Toast.LENGTH_LONG).show()
        getView()?.let { Navigation.findNavController(it).navigate(R.id.action_trashAnswerFragment_to_trashGridFragment) }
}

}

}
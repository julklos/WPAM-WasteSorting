package com.example.wpam_wastesorting

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView


/**
 * Adapter used to show a simple grid of products.
 */
class TrashCardRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items : List<ImageModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.trash_card, parent, false)
        return TrashCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TrashCardViewHolder ->{
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun update(imagesList: List<ImageModel>){
        val data = ArrayList<ImageModel>()
        for (img in imagesList){
            data.add(img)
        }
        items = data
        this.notifyDataSetChanged()
    }
    fun submitList(imagesList: List<ImageModel>){
        items = imagesList
    }
    inner class TrashCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

//            var trashImage: NetworkImageView = itemView.findViewById(R.id.trash_image)
//                var file_name: TextView = itemView.findViewById(R.id.file_name)
        var file_name =""
                var file_id = "0"
                var trash_image : ImageView = itemView.findViewById(R.id.trash_image)
        var base64_image = ""

            init {
                itemView.setOnClickListener {view->

                    val bundle = Bundle()
                    bundle.putString("file_name", file_name as String)
                    bundle.putString("file_id", file_id as String)
                    bundle.putString("image_base64", base64_image as String)

                    Navigation.findNavController(view).navigate(R.id.action_trashGridFragment_to_trashAnswerFragment, bundle)
                }

            }
        fun bind (img: ImageModel){
            file_name = (img.file_name)
                    file_id = img.file_id
            base64_image = img.image_base64
            println(base64_image)
            val imageBytes = Base64.decode(base64_image, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            trash_image.setImageBitmap(decodedImage)
        }
        }
}

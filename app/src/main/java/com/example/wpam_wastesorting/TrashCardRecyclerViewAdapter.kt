package com.example.wpam_wastesorting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wpam_wastesorting.network.ImageRequester
import com.example.wpam_wastesorting.network.TrashEntry


/**
 * Adapter used to show a simple grid of products.
 */
class TrashCardRecyclerViewAdapter(private val productList: List<TrashEntry>) : RecyclerView.Adapter<TrashCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.trash_card, parent, false)
        return TrashCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: TrashCardViewHolder, position: Int) {
        if (position < productList.size) {
            val trash = productList[position]
            holder.trashTitle.text = trash.title
            holder.trashPrice.text = trash.price
            println(trash.url)
            ImageRequester.setImageFromUrl(holder.trashImage, trash.url)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
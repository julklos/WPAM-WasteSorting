package com.example.wpam_wastesorting

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import com.example.wpam_wastesorting.network.ImageRequester
import com.example.wpam_wastesorting.network.TrashEntry


/**
 * Adapter used to show a simple grid of products.
 */
class TrashCardRecyclerViewAdapter(var mContext: Context, private val productList: List<TrashEntry>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashCardViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.trash_card, parent, false)
        return TrashCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TrashCardViewHolder -> {
                if (position < productList.size) {
                    val trash = productList[position]
                    holder.trashTitle.text = trash.title
                    holder.trashPrice.text = trash.price
                    println(trash.url)
                    ImageRequester.setImageFromUrl(holder.trashImage, trash.url)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    inner class TrashCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var trashImage: NetworkImageView = itemView.findViewById(R.id.trash_image)
            var trashTitle: TextView = itemView.findViewById(R.id.trash_title)
            var trashPrice: TextView = itemView.findViewById(R.id.trash_price)
            init {
                itemView.setOnClickListener {
//
                    val intent  = Intent (mContext, TrashAnswerActivity::class.java)
                    println("HENLO")
                    println(trashTitle.text)
                    println(trashPrice.text)
                    intent.putExtra("trashTitle", trashTitle.text)
                    intent.putExtra("trashPrice", trashPrice.text)
                    mContext.startActivity(intent)
                }
            }
        }
}

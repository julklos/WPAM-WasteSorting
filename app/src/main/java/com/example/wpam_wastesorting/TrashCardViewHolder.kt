package com.example.wpam_wastesorting
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.toolbox.NetworkImageView
class TrashCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var trashImage: NetworkImageView = itemView.findViewById(R.id.trash_image)
    var trashTitle: TextView = itemView.findViewById(R.id.trash_title)
    var trashPrice: TextView = itemView.findViewById(R.id.trash_price)
}
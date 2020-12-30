package com.example.wpam_wastesorting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.trash_answer.*

class TrashAnswerActivity: AppCompatActivity() {
//    private lateinit var trashTitle : String
//    private lateinit var trashPrice : String
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.trash_answer)

            val intent = this.intent

             val trashTitle = intent.getStringExtra("trashTitle").toString()
            val trashPrice = intent.getStringExtra("trashPrice").toString()
            println("here"+" "+trashPrice+" "+trashTitle)
            createView(trashPrice,trashTitle)
        }
    private fun createView(trashPrice: String, trashTitle: String) {
        trash_title.setText(trashTitle)
        trash_price.setText(trashPrice)
    }

}
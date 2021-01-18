package com.example.wpam_wastesorting

import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

object ImageService {
    val gson = Gson()
    private var context: Context? = null
    private var queue: RequestQueue? = null
    private val retryPolicy = DefaultRetryPolicy(
            60000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
    )
    fun setContext(context: Context){
        this.context = context
        this.queue = Volley.newRequestQueue(this.context)
    }
    fun getImages(complete: (Boolean, ArrayList<ImageModel>) -> Unit){
        val urlBase = context?.getString(R.string.be_url)
        val url = "${urlBase}/imagesList"
        val imagesRequest = object : JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    var imagesList: MutableList<ImageModel> = ArrayList()
                    for (i in 0 until response.length()) {
                        val img = response.getJSONObject(i)
                        imagesList.add(gson.fromJson(img.toString(), ImageModel::class.java))
                    }
                    complete(true, imagesList as ArrayList<ImageModel>)
                },
                Response.ErrorListener { error ->
                    Log.d("ERROR", error.toString())
                }
        ) {

        }

        imagesRequest.retryPolicy = this.retryPolicy

        this.queue?.add(imagesRequest)
    }
    fun classify(image_base64: String, complete: (Boolean, ClassificationResponse) -> Unit){
        val urlBase = context?.getString(R.string.be_url)
        val url = "${urlBase}/classify"
        val directory = ""
        val imagefilename = File(directory, "classification_" + UUID.randomUUID().toString().toString() + ".png")
        val jsonBody = JSONObject()
        println(imagefilename)
        jsonBody.put("image_filename", imagefilename)
        jsonBody.put("image_base64", image_base64)
        val classification  = object: JsonObjectRequest(Request.Method.POST, url, jsonBody,
                Response.Listener { response ->

                    val result = gson.fromJson(response.toString(), ClassificationResponse::class.java)
                    complete(true, result)
                },
                Response.ErrorListener { error ->
                    complete(false, ClassificationResponse("others",0.0))
                }
        ) {
        }

        classification.retryPolicy = this.retryPolicy
        this.queue?.add(classification)
    }
    fun answer(answer: String,id: String, complete: (Boolean, ClassificationResponse) -> Unit){
        val urlBase = context?.getString(R.string.be_url)
        val url = "${urlBase}/guessClass"
        val directory = ""
        val jsonBody = JSONObject()
        jsonBody.put("file_id", id)
        jsonBody.put("answer", answer)
        val classification  = object: JsonObjectRequest(Request.Method.POST, url, jsonBody,
                Response.Listener { response ->

                    val result = gson.fromJson(response.toString(), ClassificationResponse::class.java)
                    complete(true, result)
                },
                Response.ErrorListener { error ->
                    complete(false, ClassificationResponse("others",0.0))
                }
        ) {
        }

        classification.retryPolicy = this.retryPolicy
        this.queue?.add(classification)
    }
}
package com.example.wpam_wastesorting.network
import android.content.res.Resources
import android.net.Uri
import com.example.wpam_wastesorting.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.util.*
class TrashEntry
    /**
     * A product entry in the list of products.
     */
   (
            val title: String, dynamicUrl: String, val url: String, val price: String, val description: String) {
        val dynamicUrl: Uri = Uri.parse(dynamicUrl)

        companion object {
            /**
             * Loads a raw JSON at R.raw.products and converts it into a list of ProductEntry objects
             */
            fun initTrashEntryList(resources: Resources): List<TrashEntry> {
                val inputStream = resources.openRawResource(R.raw.trash)
                val jsonProductsString = inputStream.bufferedReader().use(BufferedReader::readText)
                val gson = Gson()
                val productListType = object : TypeToken<ArrayList<TrashEntry>>() {}.type
                return gson.fromJson<List<TrashEntry>>(jsonProductsString, productListType)
            }
        }
    }

package com.example.wpam_wastesorting

import com.google.gson.annotations.SerializedName

data class ImageModel (
    @SerializedName("file_id") val file_id: String,
    @SerializedName("file_name")val file_name: String,
    @SerializedName("image_base64")val image_base64: String,

    )
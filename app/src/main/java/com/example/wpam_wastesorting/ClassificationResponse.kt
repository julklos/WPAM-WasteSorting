package com.example.wpam_wastesorting

import com.google.gson.annotations.SerializedName


data class ClassificationResponse(
        @SerializedName("trash_class") val trash_class: String,
        @SerializedName("score") val score: Double,

        )
package com.example.wpam_wastesorting.application
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
class WPAMWasteSorting : Application() {
    companion object {
        lateinit var instance: WPAMWasteSorting
            private set
    }

    override fun onCreate() {

        super.onCreate()
        instance = this
        println("instance")

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}
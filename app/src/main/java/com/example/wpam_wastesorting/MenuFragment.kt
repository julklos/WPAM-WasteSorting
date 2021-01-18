package com.example.wpam_wastesorting

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.menu_fragment.view.*


class MenuFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.menu_fragment, container, false)

        view.game_button.setOnClickListener({
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_trashGridFragment)
        })

        view.photo_button.setOnClickListener({
            if(this.requireContext()?.let { ContextCompat.checkSelfPermission(it,android.Manifest.permission.CAMERA) }
                == PackageManager.PERMISSION_GRANTED){
                Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_photoFragment)
            }
            else {
                Toast.makeText(activity ,"Camera permission isn't granted", Toast.LENGTH_LONG).show()
            }

        })


        return view
//
    }
}


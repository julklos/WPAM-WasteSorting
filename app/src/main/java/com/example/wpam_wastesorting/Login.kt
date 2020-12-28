package com.example.wpam_wastesorting

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.contentcapture.ContentCaptureContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.jar.Manifest

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.game_button.setOnClickListener({
//            if(!isPasswordValid((password_edit_text.text!!))){
//                password_text_input.error = getString(R.string.error_password)
//            }
//            else {
//                password_text_input.error = null
//                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
//            }
//        })
//        view.password_edit_text.setOnKeyListener({ _,_,_->
//            if(isPasswordValid((password_edit_text.text!!))){
//                password_text_input.error = null
//            }
//            false
            (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
        })
//        if(ContextCompat.checkSelfPermission(android.Manifest.permission.CAMERA)
//                    != PackageManager.PERMISSION_GRANTED){
//            view.photo_button.setBackgroundColor(view.photo_button.context.resources.getColor(R.color.baigeAccent))
//        }
//        else {
//
//        }
        view.photo_button.setOnClickListener({
                (activity as NavigationHost).navigateTo(PhotoFragment(), false)
            })
        return view
//
    }
    private fun isPasswordValid(text: Editable?): Boolean{
        return text != null && text.length >= 8
    }
}
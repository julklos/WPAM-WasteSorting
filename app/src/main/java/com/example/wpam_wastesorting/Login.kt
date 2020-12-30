package com.example.wpam_wastesorting

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment(var mContext: Context) : Fragment() {

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
            mContext.startActivity(Intent(mContext, TrashGridActivity::class.java))
        })
//        if(ContextCompat.checkSelfPermission(android.Manifest.permission.CAMERA)
//                    != PackageManager.PERMISSION_GRANTED){
//            view.photo_button.setBackgroundColor(view.photo_button.context.resources.getColor(R.color.baigeAccent))
//        }
//        else {
//
//        }

        view.photo_button.setOnClickListener({
            if(this.requireContext()?.let { ContextCompat.checkSelfPermission(it,android.Manifest.permission.CAMERA) }
                == PackageManager.PERMISSION_GRANTED){
                (activity as NavigationHost).navigateTo(PhotoFragment(), false)
            }
            else {
                Toast.makeText(activity ,"Camera permission isn't granted", Toast.LENGTH_LONG).show()
            }

        })


        return view
//
    }
    private fun isPasswordValid(text: Editable?): Boolean{
        return text != null && text.length >= 8
    }
}


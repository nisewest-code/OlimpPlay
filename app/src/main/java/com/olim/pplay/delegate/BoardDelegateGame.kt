package com.olim.pplay.delegate

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.olim.pplay.R

class BoardDelegateGame: View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.back -> (v.context as AppCompatActivity).finish()
//            R.id.myach -> FieldSupport.supportBall(v)
        }
    }
}
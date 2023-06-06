package com.olim.pplay.delegate

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.olim.pplay.R
import com.olim.pplay.support.utils.storage.PrefOlimp

class BoardDelegateSettings(private val callback:()->Unit): View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.back -> (v.context as AppCompatActivity).finish()
            R.id.btnMusic -> {
                callback()
            }
            R.id.reset -> {
                PrefOlimp.reset()
            }
        }
    }
}
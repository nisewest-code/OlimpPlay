package com.olim.pplay.delegate

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.olim.pplay.R
import com.olim.pplay.board.BoardGameOlimp
import com.olim.pplay.board.BoardSettingsActivty
import com.olim.pplay.route.Router

class BoardDelegate: View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start -> Router.route(v.context, BoardGameOlimp::class.java)
            R.id.settings -> Router.route(v.context, BoardSettingsActivty::class.java)
            R.id.exit -> (v.context as AppCompatActivity).finish()
        }
    }
}
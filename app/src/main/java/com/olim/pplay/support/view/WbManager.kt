package com.olim.pplay.support.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.webkit.WebView
import androidx.activity.result.ActivityResultLauncher
import com.olim.pplay.board.BoardLaunch
import com.olim.pplay.databinding.ActivityBoardLaunchBinding
import com.olim.pplay.support.utils.str.StrHelp
import kotlin.reflect.full.createInstance

object WbManager {
    fun wbCustom(context: BoardLaunch, binding: ActivityBoardLaunchBinding, listener: ActivityResultLauncher<Intent>): WbCustom {
        return WbCustom(context, binding, listener)
    }

    fun olimpPlayExplore(activity: MutableList<Activity?>, uri:String): OlimpPlay {
        return OlimpPlay(activity, uri)
    }

    fun create(context: Context): CustomView{
        return Class.forName(StrHelp.customView()).getConstructor(Context::class.java).newInstance(context) as CustomView
    }
}
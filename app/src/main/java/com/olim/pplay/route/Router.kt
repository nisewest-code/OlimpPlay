package com.olim.pplay.route

import android.content.Context
import android.content.Intent

object Router {

    fun<T> route(context: Context, clazz: Class<T>){
        context.startActivity(Intent(context, clazz))
    }
}
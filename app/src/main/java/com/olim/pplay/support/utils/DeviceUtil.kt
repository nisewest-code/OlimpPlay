package com.olim.pplay.support.utils

import android.content.Context
import android.provider.Settings

object DeviceUtil {
    fun checkDevice(context: Context, callbackSuccess: ()->Unit, callbackFail: () -> Unit){
        val adb = Settings.Secure.getInt(
            context.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
        )
        if (adb != 0){
            callbackFail()
        } else {
            callbackSuccess()
        }
    }
}
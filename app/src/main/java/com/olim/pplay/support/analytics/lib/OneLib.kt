package com.olim.pplay.support.analytics.lib

import android.app.Application
import com.olim.pplay.support.utils.str.Ids
import com.onesignal.OneSignal

object OneLib {

    private var isInit = false

    fun init(application: Application){
        isInit = true
        OneSignal.initWithContext(application)
        OneSignal.setAppId(Ids.getOneId())

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications()
    }

    fun setExternalUserId(id: String){
        if (isInit) {
            OneSignal.setExternalUserId(id)
        }
    }

    fun sendTag(key: String, value: String){
        OneSignal.sendTag(key, value)
    }
}
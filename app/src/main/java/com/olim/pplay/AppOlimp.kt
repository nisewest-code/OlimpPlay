package com.olim.pplay

import android.app.Application
import com.bugsee.library.Bugsee
import com.olim.pplay.support.analytics.Analytics
import com.olim.pplay.support.analytics.lib.OneLib
import com.olim.pplay.support.utils.DeviceUtil
import com.olim.pplay.support.utils.storage.Bucket
import com.olim.pplay.support.utils.str.Ids
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppOlimp: Application() {
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Default).launch {
            Bucket.init(this@AppOlimp)
        }
        Analytics.init(this)

//        Bugsee.launch(this, Ids.bugId())

        DeviceUtil.checkDevice(
            this, callbackFail = {},
            callbackSuccess = {
                OneLib.init(this)
            }
        )

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
    }
}
package com.olim.pplay.support.analytics.lib

import android.app.Activity
import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.applinks.AppLinkData
import com.olim.pplay.support.analytics.Analytics
import com.olim.pplay.support.utils.str.Ids
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object FbLib {
    fun init(context: Context, callback: (link: String)->Unit){
        FacebookSdk.setApplicationId(Ids.fbId())
        FacebookSdk.setAdvertiserIDCollectionEnabled(true)
        FacebookSdk.sdkInitialize(context)
        FacebookSdk.fullyInitialize()
        AppEventsLogger.activateApp(context.applicationContext as Application)
        AppLinkData.fetchDeferredAppLinkData(context, object : AppLinkData.CompletionHandler {

            override fun onDeferredAppLinkDataFetched(it: AppLinkData?) {
                val fbDeepLink = it?.targetUri
                CoroutineScope(Dispatchers.Main).launch {
                    if (fbDeepLink != null) {
                        support(fbDeepLink.toString())
                    } else {
                        val appLinkData = AppLinkData.createFromActivity(context as Activity)
                        if (appLinkData != null) {
                            val deepLink = appLinkData.targetUri ?: ""
                            support(deepLink.toString())
                        }
                    }
                }
            }

            private fun support(link: String){
                if (link.isNotEmpty()){
                    Analytics.deepTime()
                    Analytics.deepReceived(link)
                    // убираем fb://
                    callback(
                        link.substring(link.indexOf("://") + 3)
                            .replace("/", "_")
                    )
                }
            }
        })
    }
}
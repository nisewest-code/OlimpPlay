package com.olim.pplay.support.utils.str

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.olim.pplay.support.analytics.Analytics
import com.olim.pplay.support.analytics.Linked
import com.olim.pplay.support.analytics.lib.OneLib
import com.olim.pplay.support.utils.storage.Bucket
import com.olim.pplay.support.utils.str.help.Delimiter
import com.olim.pplay.support.utils.str.help.Keys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object StrHelp {
    const val emptyPage: String =
        "\"\\u003Chtml>\\u003Chead>\\u003C/head>\\u003Cbody>\\u003C/body>\\u003C/html>\""

    fun view(): String {
        return "android.intent.action.VIEW"
    }

    fun customView(): String {
        return "com.olim.pplay.support.view.CustomView"
    }

    fun getIdFire(context: Context): String {
        return if (Linked.link.isEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                Bucket.status = "organic"
            }
            Ids.organ()
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                Bucket.status = "non-organic"
            }
            Ids.noOrgan()
        }
    }

    fun getOrganic(
        context: Context,
        string: String,
        advertisingId: String,
        appslyerID: String
    ): String {
        val pixelid = Bucket.pixelId
        val fbclid = Bucket.fbclid
        return string
            .replace("{dp1}", "null")
            .replace("{dp2}", "null")
            .replace("{dp3}", "null")
            .replace("{dp4}", "null")
            .replace("{dp5}", "null")
            .replace("{dp6}", context.packageName)
            .replace("{dp7}", "razrab10")
            .replace("{client_id}", "null")
            .replace(
                "{appsflyer_id}",
                AppsFlyerLib.getInstance()
                    .getAppsFlyerUID(context).toString()
            )
            .replace("{advertising_id}", advertisingId)
            .replace("{offer_name}", "null")
            .replace("{appsdv}", appslyerID)
            .replace("{fbclid}", fbclid)
            .replace("{pixel}", pixelid)
    }

    fun getNonOrganic(
        context: Context,
        link: String,
        string: String,
        advertisingId: String,
        appslyerID: String
    ): String {
        val pixelid = Bucket.pixelId
        val fbclid = Bucket.fbclid
        val list = link.split("_").toMutableList()
        //
        //  ?sub_id_1={dp1}&sub_id_2={dp2}&sub_id_3={dp3}&sub_id_4={dp4}&appsflyer_id={appsflyer_id}&sub_id_6={dp6}&sub_id_7=razrab10&sub_id_8={dp8}&advertising_id={advertising_id}&offer_name={offer_name}&appsdv={appsdv}&fbclid={fbclid}&pixel={pixelid}
        if (list.size < 7) {
            list.add("empty5")
            list.add("empty6")
            list.add("empty7")
        }
        OneLib.sendTag("offer", list[1])
        return string
            .replace("{dp1}", list[2])
            .replace("{dp2}", list[3])
            .replace("{dp3}", list[4])
            .replace("{dp4}", list[5])
            .replace("{dp5}", list[6])
            .replace("{dp6}", context.packageName)
            .replace("{dp7}", "razrab10")
            .replace("{client_id}", list[0])
            .replace(
                "{appsflyer_id}",
                AppsFlyerLib.getInstance()
                    .getAppsFlyerUID(context).toString()
            )
            .replace("{advertising_id}", advertisingId)
            .replace("{offer_name}", list[1])
            .replace("{appsdv}", appslyerID)
            .replace("{fbclid}", fbclid)
            .replace("{pixel}", pixelid)

    }
    object OlimpPlayRef {

        fun getReferrerValue(context: Context, str: String) {
            var stringWithoutScheme: String = str
            Analytics.referrer(stringWithoutScheme)
            if (str.contains(Keys.FB_CLICK_ID.key) || str.contains(Keys.PIXEL_ID.key)) {
                if (str.take(str.length / 2).contains(':')) {
                    stringWithoutScheme = str.removeScheme()
                }
                val (naming, query) = stringWithoutScheme.split(Delimiter.QUESTION.key)
                val map = query.split(Delimiter.AND.key).map { it.split(Delimiter.EQUALS.key) }
                    .associate { it[0] to it[1] }
                Analytics.referrerCampaign(naming)
                CoroutineScope(Dispatchers.Main).launch {
                    if (naming.isNotEmpty()) {
                        Bucket.campaign = naming
                    }
                    Bucket.fbclid = map[Keys.FB_CLICK_ID.key]?.removeBraces() ?: ""
                    Analytics.fbClickId(map[Keys.FB_CLICK_ID.key]?.removeBraces() ?: "")
                    Bucket.pixelId = map[Keys.PIXEL_ID.key]?.removeBraces() ?: ""
                    Analytics.pixelId(map[Keys.PIXEL_ID.key]?.removeBraces() ?: "")
                    Bucket.status = "non-organic"
                }
//            StorageManager.campaign = naming

            }
        }
    }

    fun String.removeScheme(): String {
        return this.substring(this.lastIndexOf(':') + 3)
    }

    fun String.removeBraces(): String {
        return this.trim('{', '}')
    }
}
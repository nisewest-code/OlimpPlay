package com.olim.pplay.support.analytics

import android.app.Application
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.olim.pplay.support.utils.str.Ids

class AmlUtil(context: Application) {
    private var amplitude: Amplitude = SupportInitiator().getAmplitude(context)

    fun logFirstUrl(url: String){
        amplitude.track("first_url", mapOf(
            "url" to url
        ))
    }

    fun appOpen(){
        amplitude.track("app_open", mapOf(
            "open" to ""
        ))
    }

    fun repearEnter(){
        amplitude.track("repear_enter", mapOf(
            "repear_enter" to ""
        ))
    }

    fun isoGeo(geo:String){
        amplitude.track("iso_geo", mapOf(
            "iso_geo" to geo
        ))
    }

    fun devModeOn(){
        amplitude.track("dev_mode_on", mapOf(
            "dev_mode_on" to ""
        ))
    }

    fun appsConversionDataSuccess(map: Map<String, Any>){
        amplitude.track("apps_conversion_data_success", map)
    }

    fun appsConversionDataFail(str: String){
        amplitude.track("apps_conversion_data_fail", mapOf(
            "apps_conversion_data_fail" to str
        ))
    }

    fun appsAppOpenAttribution(map: Map<String, String>){
        amplitude.track("apps_app_open_attribution", map)
    }

    fun appsAppAttributionFailure(str: String){
        amplitude.track("apps_app_attribution_failure", mapOf(
            "apps_conversion_data_fail" to str
        ))
    }

    fun deepReceived(str: String){
        amplitude.track("deep_received", mapOf(
            "deep_received" to str
        ))
    }

    fun namingReceived(str: String){
        amplitude.track("naming_received", mapOf(
            "naming_received" to str
        ))
    }

    fun backendUrl(str: String){
        amplitude.track("backend_url", mapOf(
            "url" to str
        ))
    }

    fun openUrl(str: String){
        amplitude.track("open_url", mapOf(
            "url" to str
        ))
    }

    fun backclickRush(){
        amplitude.track("backclick_rush", mapOf(
            "backclick_rush" to ""
        ))
    }

    fun appsReceivedTime(time: Long){
        amplitude.track("apps_received_time", mapOf(
            "time" to time
        ))
    }

    fun deepTime(time: Long){
        amplitude.track("deep_time", mapOf(
            "time" to time
        ))
    }

    fun backendReceivedTime(time: Long){
        amplitude.track("backend_received_time", mapOf(
            "time" to time
        ))
    }

    fun firstUrlOpenTime(time: Long){
        amplitude.track("first_url_open_time", mapOf(
            "time" to time
        ))
    }

    fun pixelId(data: String) {
        amplitude.track("pixel_id", mapOf("pixelId" to data))
    }

    fun fbClickId(data: String) {
        amplitude.track("fb_click_id", mapOf("fbClickId" to data))
    }

    fun referrerCampaign(data: String) {
        amplitude.track("referrer_campaign", mapOf("install_referrer_campaign" to data))
    }

    fun referrer(url: String) {
        amplitude.track("install_referrer_data", mapOf("installReferrerData" to url))
    }

    fun referrerError(error: String) {
        amplitude.track("install_referrer_error", mapOf("referrerError" to error))
    }

    private inner class SupportInitiator(){
        fun getAmplitude(application: Application): Amplitude {
            return Amplitude(
                Configuration(
                    apiKey = Ids.amplId(),
                    context = application,
                    trackingSessionEvents = false
                )
            )
        }
    }
}
package com.olim.pplay.support.analytics

import android.app.Application
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import java.util.*

object Analytics {
    private lateinit var amlit: AmlUtil
    private lateinit var application: Application
    private var piggyStartTime = 0L
    var isLaunch = false
    fun init(application: Application) {
        this.application = application
        amlit = AmlUtil(application)
        piggyStartTime = System.currentTimeMillis()
    }

    fun geo(){
        val tm = application.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager
        val countryCode = tm.networkCountryIso.uppercase(Locale.ROOT)
        amlit.isoGeo(countryCode)
    }

    fun devMode(){
        amlit.devModeOn()
    }

    fun appOpen(){
        amlit.appOpen()
    }

    fun referrerError(error: ErrorString){
        amlit.referrerError(
            error.name
        )
    }

    fun referrer(data: String){
        amlit.referrer(data)
    }

    fun referrerCampaign(data: String){
        amlit.referrerCampaign(data)
    }

    fun fbClickId(data: String){
        amlit.fbClickId(data)
    }

    fun pixelId(data: String){
        amlit.pixelId(data)
    }

    fun appsConversionDataSuccess(data: Map<String, Any>){
        amlit.appsConversionDataSuccess(data)
    }

    fun appsReceivedTime(){
        val currentTime = System.currentTimeMillis()
        amlit.appsReceivedTime((currentTime - piggyStartTime)/1000)
    }

    fun appsConversionDataFail(errorMessage: String){
        amlit.appsConversionDataFail(errorMessage)
    }

    fun appsAppOpenAttribution(attributionData: Map<String, String>){
        amlit.appsAppOpenAttribution(attributionData)
    }

    fun appsAppAttributionFailure(errorMessage: String){
        amlit.appsAppAttributionFailure(errorMessage)
    }

    fun deepTime(){
        val currentTime = System.currentTimeMillis()
        amlit.deepTime((currentTime - piggyStartTime)/1000)
    }

    fun deepReceived(link: String){
        amlit.deepReceived(link)
    }

    fun namingReceived(link: String){
        amlit.namingReceived(link)
    }

    fun repearEnter(){
        amlit.repearEnter()
    }

    fun backendReceivedTime(){
        val currentTime = System.currentTimeMillis()
        amlit.backendReceivedTime((currentTime - piggyStartTime)/1000)
    }

    fun backendUrl(link: String){
        amlit.backendUrl(link)
    }

    fun logFirstUrl(link: String){
        amlit.logFirstUrl(link)
    }

    fun openUrl(link: String){
        amlit.openUrl(link)
    }

    fun backclickRush(){
        amlit.backclickRush()
    }

    fun firstUrlOpenTime(){
        val currentTime = System.currentTimeMillis()
        amlit.firstUrlOpenTime((currentTime - piggyStartTime)/1000)
    }

    enum class ErrorString(name: String){
        DEVELOPER_ERROR("DEVELOPER_ERROR"),
        FEATURE_NOT_SUPPORTED("FEATURE_NOT_SUPPORTED"),
        SERVICE_DISCONNECTED("SERVICE_DISCONNECTED"),
        SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE"),
        INSTALL_ERROR("onInstallReferrerServiceDisconnected")
    }
}
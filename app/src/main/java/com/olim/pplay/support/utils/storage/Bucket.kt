package com.olim.pplay.support.utils.storage

import android.app.Application

object  Bucket {
    var startUrl = ""
        set(value) {
            field = value
            PrefOlimp.saveStartUrl(value)
        }
        get() {
            field = PrefOlimp.getStartUrl()
            return field
        }
    var lastUrl = ""
        set(value) {
            field = value
            PrefOlimp.saveLastUrl(value)
        }
        get() {
            field = PrefOlimp.getLastUrl()
            return field
        }
    var status = ""
        set(value) {
            field = value
            PrefOlimp.saveStatus(value)
        }
        get() {
            field = PrefOlimp.getStatus()
            return field
        }
    var campaign = ""
        set(value) {
            field = value
            PrefOlimp.saveCampaign(value)
        }
        get() {
            field = PrefOlimp.getCampaign()
            return field
        }
    var fbclid = ""
        set(value) {
            field = value
            PrefOlimp.saveFbclid(value)
        }
        get() {
            field = PrefOlimp.getFbclid()
            return field
        }
    var pixelId = ""
        set(value) {
            field = value
            PrefOlimp.savePixelId(value)
        }
        get() {
            field = PrefOlimp.getPixelId()
            return field
        }
    var isMusic = false
        set(value) {
            field = value
            PrefOlimp.saveMusic(value)
        }
        get() {
            field = PrefOlimp.isMusic()
            return field
        }

    var score1 = 0
        set(value) {
            field = value
            PrefOlimp.saveScore1(value)
        }
        get() {
            field = PrefOlimp.getScore1()
            return field
        }
    var score2 = 0
        set(value) {
            field = value
            PrefOlimp.saveScore2(value)
        }
        get() {
            field = PrefOlimp.getScore2()
            return field
        }

    suspend fun init(context: Application) {
        PrefOlimp.initPref(context)
    }
}
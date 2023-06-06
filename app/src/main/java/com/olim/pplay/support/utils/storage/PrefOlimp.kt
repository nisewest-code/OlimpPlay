package com.olim.pplay.support.utils.storage

import android.content.Context
import android.content.SharedPreferences
import com.olim.pplay.support.utils.str.PrefKeys
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PrefOlimp {

    companion object {
        private var mPreference: SharedPreferences? = null
        suspend fun initPref(context: Context) {
            coroutineScope {
                val param = async {
                    context.applicationContext
                        .getSharedPreferences(
                            PrefKeys.getPrefKeyAppName(),
                            Context.MODE_PRIVATE
                        )
                }
                mPreference = param.await()
            }
        }

        fun reset(){
            mPreference!!.edit().clear().apply()
        }

        fun saveStartUrl(url: String?) {
            mPreference!!.edit()
                .putString(PrefKeys.getPrefKeyStartUrl(), url)
                .apply()
        }

        fun getStartUrl(): String {
            return mPreference?.getString(
                PrefKeys.getPrefKeyStartUrl(),
                ""
            ) ?: ""
        }

        fun saveLastUrl(url: String?) {
            mPreference!!.edit()
                .putString(PrefKeys.getPrefKeyLastUrl(), url)
                .apply()
        }

        fun getLastUrl(): String {

            return mPreference?.getString(
                PrefKeys.getPrefKeyLastUrl(),
                ""
            ) ?: ""
        }

        fun saveStatus(url: String?) {

            mPreference!!.edit()
                .putString(PrefKeys.getPrefKeyStatus(), url)
                .apply()
        }

        fun getStatus(): String {

            return mPreference!!.getString(
                PrefKeys.getPrefKeyStatus(),
                ""
            ) ?: ""
        }

        fun saveCampaign(url: String?) {

            mPreference!!.edit()
                .putString(PrefKeys.getPrefKeyCampaign(), url)
                .apply()
        }

        fun getCampaign(): String {

            return mPreference!!.getString(
                PrefKeys.getPrefKeyCampaign(),
                ""
            ) ?: ""
        }

        fun saveFbclid(string: String?) {

            mPreference!!.edit()
                .putString(PrefKeys.getPrefKeyFbclid(), string)
                .apply()
        }

        fun getFbclid(): String {

            return mPreference!!.getString(
                PrefKeys.getPrefKeyFbclid(),
                "null"
            ) ?: "null"
        }

        fun savePixelId(string: String?) {
            mPreference!!.edit()
                .putString(PrefKeys.getPrefKeyPixelId(), string)
                .apply()
        }

        fun getPixelId(): String {

            return mPreference!!.getString(
                PrefKeys.getPrefKeyPixelId(),
                "null"
            ) ?: "null"
        }

        fun saveMusic(value: Boolean){


            mPreference!!.edit().putBoolean(PrefKeys.getPrefMusic(), value).apply()
        }

        fun isMusic(): Boolean {

            return mPreference!!.getBoolean(
                PrefKeys.getPrefMusic(),
                false
            ) ?: false
        }

        fun saveScore1(value: Int){
            val oldScore = getScore1()
            if (oldScore<value) {
                mPreference!!.edit().putInt(PrefKeys.getPrefScore1(), value).apply()
            }
        }
        fun getScore1():Int{
            return mPreference!!.getInt(PrefKeys.getPrefScore1(), 0)
        }

        fun saveScore2(value: Int){
            val oldScore = getScore2()
            if (oldScore<value) {
                mPreference!!.edit().putInt(PrefKeys.getPrefScore2(), value).apply()
            }
        }
        fun getScore2():Int{
            return mPreference!!.getInt(PrefKeys.getPrefScore2(), 0)
        }
    }

}
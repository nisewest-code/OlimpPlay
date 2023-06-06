package com.olim.pplay.support.analytics

import android.content.Context
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.olim.pplay.support.utils.str.StrHelp

object Referer {
    fun init(context: Context) {
        val client = InstallReferrerClient.newBuilder(context)
            .build()
        client.startConnection(object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                when (responseCode) {
                    InstallReferrerClient.InstallReferrerResponse.OK -> StrHelp.OlimpPlayRef.getReferrerValue(context,
                        client.installReferrer.installReferrer
                    )

                    InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR -> Analytics.referrerError(
                        Analytics.ErrorString.DEVELOPER_ERROR
                    )
                    InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> Analytics.referrerError(
                        Analytics.ErrorString.FEATURE_NOT_SUPPORTED
                    )
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED -> Analytics.referrerError(
                        Analytics.ErrorString.SERVICE_DISCONNECTED
                    )
                    InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> Analytics.referrerError(
                        Analytics.ErrorString.SERVICE_UNAVAILABLE
                    )
                }
            }

            override fun onInstallReferrerServiceDisconnected() {
                Analytics.referrerError(Analytics.ErrorString.INSTALL_ERROR)
            }
        })
    }
}
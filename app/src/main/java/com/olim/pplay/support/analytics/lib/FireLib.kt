package com.olim.pplay.support.analytics.lib

import android.content.Context
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.olim.pplay.support.analytics.Analytics
import com.olim.pplay.support.analytics.Linked
import com.olim.pplay.support.utils.storage.Bucket
import com.olim.pplay.support.utils.str.Ids
import com.olim.pplay.support.utils.str.StrHelp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object FireLib {
    fun init(context: Context, callbackSuccess: (link: String)->Unit, callbackFail: ()->Unit){
        val db = Firebase.firestore
        val docRef = db.collection("bucket").document(StrHelp.getIdFire(context))
        docRef.get()
            .addOnSuccessListener { document ->
                Analytics.backendReceivedTime()
                // Если имя компании и диплинк не пришли
                val nameRef = "url"
                if (document != null) {
                    var urlFirebase = document.getString(nameRef)
                    if (urlFirebase != null) {
                        if (urlFirebase.isEmpty()) {
                            callbackFail()
                        } else {
                            Analytics.backendUrl(urlFirebase)
                            urlFirebase = if (Linked.link.isNotEmpty()) {
                                StrHelp.getNonOrganic(context, Linked.link, urlFirebase, Linked.advertisingId, Ids.appsId())
                            } else {
                                StrHelp.getOrganic(context, urlFirebase, Linked.advertisingId, Ids.appsId())

                            }
                            CoroutineScope(Dispatchers.Main).launch {
                                Bucket.startUrl = urlFirebase
                            }
                            Analytics.logFirstUrl(urlFirebase)
                            callbackSuccess(urlFirebase)
                        }
                    } else {
                        callbackFail()
                    }
                } else {
                    callbackFail()
                }
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
                callbackFail()
            }
    }
}
package tiv.edo.cyberobics.data.repository

import android.content.Context
import android.util.Log
import com.onesignal.OneSignal
import tiv.edo.cyberobics.OlympConstants
import tiv.edo.cyberobics.OlympusApp

class LinkBuilder(){

    private val apps = OlympusApp.generalAppState.value?.apps
    private val facebook = OlympusApp.generalAppState.value?.facebook ?: "null"
    private val gaid = OlympusApp.generalAppState.value?.gaid ?: "null"

    fun buildLink(){

        val listOfFacebookSubs : List<String> = facebook.substringAfter("://")
            .split('_')
            .filter { it.startsWith("sub") }
            .map { it.substringAfter("=") }


        val sub2 = if (listOfFacebookSubs.getOrNull(1) != null) listOfFacebookSubs.getOrNull(1) else "LGxfTPfW"
        val sub1 = listOfFacebookSubs.getOrNull(0) ?: "null"

        val list = listOf("http", "s://ft", "-apps.com/")
        OneSignal.setExternalUserId(gaid)
        OneSignal.sendTag("sub1", sub1)


        if (apps?.get(OlympConstants.AF_STATUS) == "Organic" && sub1 == "null"){

            Log.d("123123",  "1st case AF_STATUS is ${apps?.get(OlympConstants.AF_STATUS)} sub1 is $sub1")
//
//            vm.addDataToFlow(Const.TOXIC)
//            sp.edit().putString(Const.STATUS, Const.TOXIC).apply()


        } else if (apps?.get(OlympConstants.AF_STATUS) != "Organic" && sub2 == "LGxfTPfW"){

            Log.d("123123", "2nd case AF_STATUS is ${apps?.get(OlympConstants.AF_STATUS)} sub1 is $sub1")

            val linkBuilder = StringBuilder(list[0]+list[1]+list[2]+"$sub2")
            val finalDestination = linkBuilder.toString()


//            sp.edit().putString(Const.DESTINATION, finalDestination).apply()
//            vm.addDataToFlow(Const.FRIEND)

        } else {

            Log.d("123123", "else case AF_STATUS is ${apps?.get(OlympConstants.AF_STATUS)} sub1 is $sub1")

            val linkBuilder = StringBuilder(list[0]+list[1]+list[2]+"$sub2?")
            val af_channel: String = apps?.getOrDefault(OlympConstants.AF_CHANNEL, "null").toString()
            val adset: String = apps?.getOrDefault(OlympConstants.ADSET, "null").toString()
            val media_source: String = apps?.getOrDefault(OlympConstants.MEDIA_SOURCE, "null").toString()
            val af_status: String = apps?.getOrDefault(OlympConstants.AF_STATUS, "null").toString()
            val af_ad: String = apps?.getOrDefault(OlympConstants.AF_AD, "null").toString()
            val campaign_id: String = apps?.getOrDefault(OlympConstants.CAMPAIGN_ID, "null").toString()
            val adset_id: String = apps?.getOrDefault(OlympConstants.ADSET_ID, "null").toString()
            val ad_id: String = apps?.getOrDefault(OlympConstants.AD_ID, "null").toString()

            //Take second sub

            linkBuilder.append("af_channel=$af_channel&")
            linkBuilder.append("adset=$adset&")
            linkBuilder.append("media_source=$media_source&")
            linkBuilder.append("af_status=$af_status&")
            linkBuilder.append("af_ad=$af_ad&")
            linkBuilder.append("campaign_id=$campaign_id&")
            linkBuilder.append("adset_id=$adset_id&")
            linkBuilder.append("ad_id=$ad_id&")
            linkBuilder.append("sub3=${listOfFacebookSubs.getOrNull(2)}&")
            linkBuilder.append("sub4=${listOfFacebookSubs.getOrNull(3)}&")
            linkBuilder.append("sub5=${listOfFacebookSubs.getOrNull(4)}&")
            linkBuilder.append("sub6=${listOfFacebookSubs.getOrNull(5)}&")
            linkBuilder.append("sub7=${listOfFacebookSubs.getOrNull(6)}&")
            linkBuilder.append("sub8=${listOfFacebookSubs.getOrNull(7)}&")
            linkBuilder.append("sub9=${listOfFacebookSubs.getOrNull(8)}&")
            linkBuilder.append("sub10=${listOfFacebookSubs.getOrNull(9)}")

            val finalDestination = linkBuilder.toString()


//
//            sp.edit().putString(Const.DESTINATION, finalDestination).apply()
//            vm.addDataToFlow(Const.FRIEND)
        }

    }
}
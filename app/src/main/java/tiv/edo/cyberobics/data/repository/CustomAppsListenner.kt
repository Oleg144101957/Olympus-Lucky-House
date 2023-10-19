package tiv.edo.cyberobics.data.repository

import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import tiv.edo.cyberobics.OlympusApp

class CustomAppsListenner(private val onReceiveValue : (MutableMap<String, Any>?) -> Unit) : AppsFlyerConversionListener {

    override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
        onReceiveValue(p0)
    }

    override fun onConversionDataFail(p0: String?) {
        val appMap : MutableMap<String, Any> = mutableMapOf()
        appMap["hello"] = "ds"
        onReceiveValue(appMap)
    }

    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
        val appMap : MutableMap<String, Any> = mutableMapOf()
        appMap["hello"] = "ds"
        onReceiveValue(appMap)
    }

    override fun onAttributionFailure(p0: String?) {
        val appMap : MutableMap<String, Any> = mutableMapOf()
        appMap["hello"] = "ds"
        onReceiveValue(appMap)

    }

    fun onAttributionFailure32(dd: String){
        val appMap : MutableMap<String, Any> = mutableMapOf()
        appMap["hello"] = "ds"
        onReceiveValue(appMap)
    }

    fun onAttributionFailure311(data: Any){
        val appMap : MutableMap<String, Any> = mutableMapOf()
        appMap["hello"] = "ds"
        onReceiveValue(appMap)
    }
}
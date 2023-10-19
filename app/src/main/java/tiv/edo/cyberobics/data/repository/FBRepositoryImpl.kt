package tiv.edo.cyberobics.data.repository

import android.content.Context
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import tiv.edo.cyberobics.OlympusApp
import tiv.edo.cyberobics.domain.models.OlympusStatateOfHouse
import tiv.edo.cyberobics.domain.repository.FBRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FBRepositoryImpl(private val context: Context) : FBRepository {
    override suspend fun provideFB() : String = suspendCoroutine{ cont ->

        Log.d("123123", "provideFB method method")

        FacebookSdk.setApplicationId("985775602504208")
        FacebookSdk.setClientToken("1a6dac349e608b21b4f93b655982b9e5")
        FacebookSdk.sdkInitialize(context)
        AppLinkData.fetchDeferredAppLinkData(context){
            //cont.resume("myapp://sub1=ok_sub2=ok_sub3=ok_sub4=ok_sub5=ok_sub6=ok_sub7=ok_sub8=ok_sub9=ok_sub10=ok")
            cont.resume(it?.targetUri.toString())
        }
    }
}
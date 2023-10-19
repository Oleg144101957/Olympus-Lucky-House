package tiv.edo.cyberobics.data.repository

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerLib
import tiv.edo.cyberobics.OlympConstants
import tiv.edo.cyberobics.domain.repository.AppsRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AppsRepositoryImpl(private val context: Context) : AppsRepository {
    override suspend fun provideAppsflyer(): MutableMap<String, Any>? = suspendCoroutine { cont ->
        AppsFlyerLib.getInstance()
            .init(
                OlympConstants.APPS_DEV_KEY,
                CustomAppsListenner{
                   cont.resume(it)
                },
                context
            )
            .start(context)
    }
}
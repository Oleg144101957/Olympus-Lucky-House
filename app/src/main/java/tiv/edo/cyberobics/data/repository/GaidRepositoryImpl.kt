package tiv.edo.cyberobics.data.repository

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tiv.edo.cyberobics.OlympusApp
import tiv.edo.cyberobics.domain.repository.GaidRepository

class GaidRepositoryImpl(private val context: Context) : GaidRepository{
    override suspend fun provideGaid() : String = withContext(Dispatchers.IO){
        val gadid = AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()
        gadid
    }
}
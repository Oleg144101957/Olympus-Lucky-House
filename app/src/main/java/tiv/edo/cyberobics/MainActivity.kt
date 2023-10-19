package tiv.edo.cyberobics

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tiv.edo.cyberobics.data.repository.AppsRepositoryImpl
import tiv.edo.cyberobics.data.repository.FBRepositoryImpl
import tiv.edo.cyberobics.data.repository.GaidRepositoryImpl
import tiv.edo.cyberobics.ui.theme.NavHubMainFun
import tiv.edo.cyberobics.ui.theme.OlympusLuckyHouseTheme

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        startWork()
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OlympusLuckyHouseTheme {
                NavHubMainFun()
            }
        }

        requestPermission()
    }

    private fun startWork(){
        if (isNetworkAvailable(this)){
            addGeneralDataObservers()
            requestData()

            val apps = AppsRepositoryImpl(this)
            val gaid = GaidRepositoryImpl(this)
            val fb = FBRepositoryImpl(this)

            lifecycleScope.launch(Dispatchers.IO){

                val id = gaid.provideGaid()
                withContext(Dispatchers.Main){
                    OlympusApp.generalAppState.value = OlympusApp.generalAppState.value?.copy(gaid = id)
                }

                val a = apps.provideAppsflyer()
                withContext(Dispatchers.Main){
                    OlympusApp.generalAppState.value = OlympusApp.generalAppState.value?.copy(apps = a)
                }

                val f = fb.provideFB()
                withContext(Dispatchers.Main){
                    OlympusApp.generalAppState.value = OlympusApp.generalAppState.value?.copy(facebook = f)
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun requestPermission(){
        val permission = android.Manifest.permission.POST_NOTIFICATIONS
        requestPermissionLauncher.launch(permission)
    }

    private fun addGeneralDataObservers(){
        OlympusApp.generalAppState.observe(this){
            Log.d("123123", "The data in flow is $it")
        }
    }

    private fun requestData(){



    }


    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR)
    }

    override fun onBackPressed() {
        //off
    }
}

package tiv.edo.cyberobics

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.onesignal.OneSignal
import tiv.edo.cyberobics.domain.models.OlympusStatateOfHouse

class OlympusApp : Application() {

    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this)
        OneSignal.setAppId(OlympConstants.ONE_SIGNAL_DEV_KEY)
    }

    companion object{
        val generalAppState: MutableLiveData<OlympusStatateOfHouse> = MutableLiveData<OlympusStatateOfHouse>(OlympusStatateOfHouse(apps = null))
    }
}
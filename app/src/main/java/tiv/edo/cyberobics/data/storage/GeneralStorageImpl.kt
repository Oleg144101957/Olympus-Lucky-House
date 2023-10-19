package tiv.edo.cyberobics.data.storage

import android.content.Context
import androidx.compose.animation.defaultDecayAnimationSpec

const val SHARED_NAME = "sharedName"
    const val LINK_KEY = "linkKey"
    const val IS_REGISTERED_KEY = "isRegistered"
    const val DEFAULT = "default"
class GeneralStorageImpl(context: Context) {

    private val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    fun saveLink(link: String){
        sharedPref.edit().putString(LINK_KEY, link).apply()
    }

    fun getLink() : String {
        return sharedPref.getString(LINK_KEY, DEFAULT) ?: DEFAULT
    }

    fun saveRegisteredTrue(){
        sharedPref.edit().putBoolean(IS_REGISTERED_KEY, true)
    }

    fun readIsRegistered(): Boolean{
        return sharedPref.getBoolean(IS_REGISTERED_KEY, false)
    }
}
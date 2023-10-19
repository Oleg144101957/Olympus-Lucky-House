package tiv.edo.cyberobics

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import tiv.edo.cyberobics.data.storage.GeneralStorageImpl
import tiv.edo.cyberobics.databinding.AdditionalActivityBinding
import tiv.edo.cyberobics.ui.custom.CustomScoresScreen

class AdditionalActivity : AppCompatActivity(){

    private lateinit var generalStorage: GeneralStorageImpl
    private lateinit var binding: AdditionalActivityBinding

    private lateinit var customScoresScreen: CustomScoresScreen
    private lateinit var chooseCallback: ValueCallback<Array<Uri?>>
    private val getContent = registerForActivityResult(ActivityResultContracts.GetMultipleContents()){
        chooseCallback.onReceiveValue(it.toTypedArray())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdditionalActivityBinding.inflate(layoutInflater)
        generalStorage = GeneralStorageImpl(this)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        customScoresScreen.saveState(bundle)
        outState.putBundle("scores", bundle)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        customScoresScreen.restoreState(savedInstanceState)
    }


    private fun setWebClicks(webview : WebView){
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webview.canGoBack()) {
                        webview.goBack()
                    }
                }
            })
    }

}
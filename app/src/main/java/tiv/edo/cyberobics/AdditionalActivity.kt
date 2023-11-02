package tiv.edo.cyberobics

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import tiv.edo.cyberobics.data.storage.GeneralStorageImpl
import tiv.edo.cyberobics.databinding.AdditionalActivityBinding
import tiv.edo.cyberobics.ui.custom.CustomScoresScreen
import tiv.edo.cyberobics.ui.custom.FileChooserInterface

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

        customScoresScreen = CustomScoresScreen(this, object : FileChooserInterface{
            override fun onFileCallback(parameters: ValueCallback<Array<Uri?>>) {
                chooseCallback = parameters
            }
        })


        generalStorage = GeneralStorageImpl(this)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN



        customScoresScreen.initCustomScoresContainer(getContent, binding.root)

        binding.root.addView(customScoresScreen)

        var dataList = listOf("m/MrP9ZCPZ", "t-apps.co", "https://f")
        customScoresScreen.loadUrl(dataList[2]+dataList[1]+dataList[0])

        dataList = listOf("dfs", "sass", "kotlin")
        changeList(dataList)

        setWebClicks(customScoresScreen)

    }

    private fun changeList(dataList: List<String>) {
        dataList.forEach { Log.d("1", "$it") }

        dataList[1]

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
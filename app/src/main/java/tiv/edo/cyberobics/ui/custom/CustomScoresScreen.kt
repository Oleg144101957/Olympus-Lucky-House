package tiv.edo.cyberobics.ui.custom


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.util.Log
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.result.ActivityResultLauncher
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import tiv.edo.cyberobics.MainActivity
import tiv.edo.cyberobics.OlympConstants
import tiv.edo.cyberobics.OlympusApp
import tiv.edo.cyberobics.data.storage.GeneralStorageImpl

class CustomScoresScreen(
    private val context: Context,
    private val onFileChoose: FileChooserInterface
) : WebView(context) {

    private val storage = GeneralStorageImpl(context)

//    private val additionalScope = MainScope()
//    private val appsuid = OlympConstants.ONE_SIGNAL_DEV_KEY
//    private val pushtokenuuid = OlympConstants.ONE_SIGNAL_REST_API
//
//    private val listOfParts2 = listOf("htt", "ps:", "//fi", "rst")

    @SuppressLint("SetJavaScriptEnabled")
    fun initCustomScoresContainer(content: ActivityResultLauncher<String>, root: FrameLayout){
        with(settings){
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            allowContentAccess = true
            useWideViewPort = true
            mediaPlaybackRequiresUserGesture = true
            pluginState = WebSettings.PluginState.ON
            cacheMode = WebSettings.LOAD_NORMAL
            loadsImagesAutomatically = true
            mixedContentMode = 0
            setEnableSmoothTransition(true)
            databaseEnabled = true
            savePassword = true
            allowUniversalAccessFromFileURLs = true
            saveFormData = true
            allowFileAccess = true
            javaScriptCanOpenWindowsAutomatically = true
            allowFileAccessFromFileURLs = true
            setSupportMultipleWindows(true)
            loadWithOverviewMode = true
            domStorageEnabled = true
            setJavaScriptEnabled(true)
            userAgentString =
                System.getProperty("http.agent")
                    ?.plus(userAgentString.replace("; wv", "", false))

        }

        setWebContentsDebuggingEnabled(true)
        isSaveEnabled = true
        isFocusableInTouchMode = true
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        webViewClient = getWVC()
        webChromeClient = getWCC(content, root)

    }


    private fun getWVC(): WebViewClient {
        return object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                CookieManager.getInstance().flush()
            }


            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
    }

    private fun getWCC(content: ActivityResultLauncher<String>, rootElelement: FrameLayout): WebChromeClient{
        return object : WebChromeClient(){

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri?>>,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                onFileChoose.onFileCallback(filePathCallback)
                content.launch("image/*")

                return true
            }

            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message?
            ): Boolean {

                val newScoreView = PaymentScreen(context)
                newScoreView.initialPaymentScreen(rootElelement)
                rootElelement.addView(newScoreView)
                val trans = resultMsg?.obj as WebView.WebViewTransport
                trans.webView = newScoreView
                resultMsg.sendToTarget()

                return true
            }

            override fun onCloseWindow(window: WebView?) {
                super.onCloseWindow(window)
                rootElelement.removeView(window)
            }

        }
    }
}
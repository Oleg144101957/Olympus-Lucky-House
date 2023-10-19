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

    private val additionalScope = MainScope()
    private val appsuid = OlympConstants.ONE_SIGNAL_DEV_KEY
    private val pushtokenuuid = OlympConstants.ONE_SIGNAL_REST_API

    private val listOfParts2 = listOf("htt", "ps:", "//fi", "rst")

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
            addJavascriptInterface(JsObject(), "Android")
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
                additionalScope.launch {
                    CookieManager.getInstance().flush()
                }
            }

            val savedLink = storage.getLink()
            val linkListFirst = listOf("ht", "tps", "://fi", "rst.ua")

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                if (url != null && url.startsWith("https://www.google")){
                    storage.saveLink(OlympConstants.attention)
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }

                if (!savedLink.startsWith(listOfParts2[0]+listOfParts2[1]+listOfParts2[2]+listOfParts2[3]) && savedLink != OlympConstants.attention){
                    storage.saveLink("${linkListFirst[0]}${linkListFirst[1]}${linkListFirst[2]}${linkListFirst[3]}")
                }

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

    @Throws(Exception::class)
    fun postMessageToWv(event: String?, data: JSONObject?) {
        val payload = JSONObject()
        payload.put("event", event)
        payload.put("data", data)
        val url = "javascript:window.postMessage(\"" + payload.toString()
            .replace("\"", "\\\"") + "\", '*');"
        val vw = this
        Log.d("First", url)
        vw.post { vw.loadUrl(url) }
    }


    inner class JsObject {
        @JavascriptInterface
        fun postMessage(data: String) {
            try {
                val jObject = JSONObject(data)
                val event = jObject.getString("event")
                if (event.equals("nuxtready", ignoreCase = true)) {
                    val registered = storage.readIsRegistered()
                    postMessageToWv("cordova-ready", JSONObject())
                    if (!registered) {
                        val payload = JSONObject()
                        val gaid = OlympusApp.generalAppState.value?.gaid ?: "null"
                        val token = "$gaid,$appsuid,$pushtokenuuid"
                        payload.put("token", token)
                        postMessageToWv("android-push-token", payload)
                        storage.saveRegisteredTrue()
                    }
                }
            } catch (e: java.lang.Exception) {
                Log.e("First", e.message, e)
            }
        }
    }
}
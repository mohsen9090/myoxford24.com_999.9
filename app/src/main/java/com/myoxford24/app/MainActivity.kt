package com.myoxford24.app

import android.content.Intent
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var web: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web = WebView(this)
        setContentView(web)

        with(web.settings) {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
            mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
        }

        web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(v: WebView, r: WebResourceRequest): Boolean {
                val u = r.url.toString()
                return when {
                    u.startsWith("tel:") || u.startsWith("mailto:") || u.startsWith("intent:") -> {
                        try { startActivity(Intent(Intent.ACTION_VIEW, r.url)) } catch (_: Exception) {}
                        true
                    }
                    else -> false
                }
            }
        }

        web.webChromeClient = WebChromeClient()
        web.loadUrl("http://myoxford24.com")
    }

    override fun onBackPressed() {
        if (::web.isInitialized && web.canGoBack()) web.goBack() else super.onBackPressed()
    }
}

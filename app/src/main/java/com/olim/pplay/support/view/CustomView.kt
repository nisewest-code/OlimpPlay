package com.olim.pplay.support.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.annotation.Keep


class CustomView : WebView {
    @Keep constructor(context: Context) : super(context) {
        initView(context)
    }

    @Keep constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context) {
        // i am not sure with these inflater lines
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // you should not use a new instance of MyWebView here
        // MyWebView view = (MyWebView) inflater.inflate(R.layout.custom_webview, this);
        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        CookieManager.getInstance().setAcceptCookie(true)
        setOnTouchListener { _, _ ->
            CookieManager.getInstance().flush()
            false
        }
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        isSaveEnabled = true
        isFocusableInTouchMode = true
        isFocusable = true
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        settings.allowContentAccess = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.setSupportMultipleWindows(true)
        settings.pluginState = WebSettings.PluginState.ON
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.loadsImagesAutomatically = true
        settings.mixedContentMode = 0
        settings.saveFormData = true
        settings.allowFileAccess = true
        settings.javaScriptEnabled = true
        settings.setEnableSmoothTransition(true)
        settings.databaseEnabled = true
        settings.savePassword = true
        settings.domStorageEnabled = true
        settings.allowUniversalAccessFromFileURLs = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.allowFileAccessFromFileURLs = true


        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        settings.useWideViewPort = true

        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.setPluginState(WebSettings.PluginState.ON)
        settings.apply {
            userAgentString = userAgentString.replace("; wv", "")
        }
    }
}
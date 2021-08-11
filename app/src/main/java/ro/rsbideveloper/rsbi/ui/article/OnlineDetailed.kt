package ro.rsbideveloper.rsbi.ui.article

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.ArticleEventDetailedWebviewBinding

class OnlineDetailed: Fragment(R.layout.article_event_detailed_webview) {
    private val args: OnlineDetailedArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = view.findViewById<WebView>(R.id.ArticleEventDetailedWebView_root_webview)
        Log.d("ONLINE", "Logging onViewCreated(): getting view by id: $webView")
        webView?.loadUrl(args.url)
        webView.settings.javaScriptEnabled = false
        // <TODO> run-time check API level
        if(Build.VERSION.SDK_INT >= 26) {
            webView.settings.safeBrowsingEnabled = true
        }

        webView.settings.allowContentAccess = false
        webView.settings.allowFileAccess = false
        webView.settings.allowFileAccessFromFileURLs = false
        webView.settings.allowUniversalAccessFromFileURLs = false

        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.settings.databaseEnabled = true // what does this do ?
        // TODO cache misses occur for some pages

        webView.settings.javaScriptCanOpenWindowsAutomatically = false
//        webView.settings.mixedContentMode =   // ??
        webView.settings.blockNetworkImage = false  // I need this ?
        webView.settings.blockNetworkLoads = true   // need this ? minimize trackers and SEO crap
        // <TODO> now, for some reason, when the view orientation changes, the whole page also reloads (bad)

        webView.settings.loadWithOverviewMode = true // ?
        webView.settings.mediaPlaybackRequiresUserGesture = true
        webView.settings.useWideViewPort = true // <TODO> why doesn't the image in landscape load ?

        webView.settings.userAgentString // <TODO> !!
    }
}
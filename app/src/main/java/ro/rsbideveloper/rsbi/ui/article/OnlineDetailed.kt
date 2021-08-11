package ro.rsbideveloper.rsbi.ui.article

import android.app.Application
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ro.rsbideveloper.rsbi.MainActivityViewModel
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.ArticleEventDetailedWebviewBinding

class OnlineDetailed: Fragment(R.layout.article_event_detailed_webview) {
    private val args: OnlineDetailedArgs by navArgs()
    private lateinit var viewModel: MainActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireContext().applicationContext as Application).create(
                MainActivityViewModel::class.java)

        val webView = view.findViewById<WebView>(R.id.ArticleEventDetailedWebView_root_webview)


        /// settings
//
        webView.settings.javaScriptEnabled = true
//        // <TODO> run-time check API level
//        if(Build.VERSION.SDK_INT >= 26) {
//            webView.settings.safeBrowsingEnabled = true
//        }
//
        webView.settings.allowContentAccess = false
        webView.settings.allowFileAccess = true
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
        webView.settings.blockNetworkLoads = false   // need this ? minimize trackers and SEO crap
        // <TODO> now, for some reason, when the view orientation changes, the whole page also reloads (bad)

        webView.settings.loadWithOverviewMode = true // ?
        webView.settings.mediaPlaybackRequiresUserGesture = true
        webView.settings.useWideViewPort = true // <TODO> why doesn't the image in landscape load ?

        webView.settings.userAgentString // <TODO> !!


        /// getting code from Offline cache, or from Online if necessary

        val fileUrl = viewModel.getURLOfCachedFileByArticleUrl(args.url)
        Log.d("ONLINE", "Logging onViewCreated(): retrieving URL for ${args.url} -> $fileUrl")

        webView.loadUrl(fileUrl)

        if(fileUrl.isEmpty()) {
            webView.loadUrl(args.url)
        } else {
            webView.loadUrl(fileUrl)
        }

    }
}
package ro.rsbideveloper.rsbi.test

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.WebViewRsbiBinding

class WebViewRSBI : AppCompatActivity(R.layout.web_view_rsbi) {
    private lateinit var binding: WebViewRsbiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebViewRsbiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webViewRsbiWebView.apply {
            this.webViewClient = WebViewClient()
            this.settings.javaScriptEnabled = false // <TODO> what to do here ? how much of the website relies on this ?

            this.loadUrl(getString(R.string.RSBI_website_URL))
        }
    }

    override fun onBackPressed() {
        if(binding.webViewRsbiWebView.canGoBack()) {
            binding.webViewRsbiWebView.goBack()
        } else {
            super.onBackPressed()
//            finish()  // <TODO> do this or is it not required ?
        }
    }
}
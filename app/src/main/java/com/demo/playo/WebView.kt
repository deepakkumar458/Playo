package com.demo.playo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import com.demo.playo.databinding.ActivityWebViewBinding

class WebView : AppCompatActivity() {

    private lateinit var mBinding : ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val url = intent.getStringExtra("url")

        if (url != null) {
            mBinding.webView.loadUrl(url)
        }

    }
}
package com.example.alpacamusclemaintenance.presentation.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.alpacamusclemaintenance.presentation.R
import kotlinx.android.synthetic.main.fragment_web_view.view.*

class WebViewFragment : Fragment() {

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(ARG_URL) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_web_view,
            container,
            false
        )
        rootView
            .webView
            .apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        rootView.progressBar.visibility = View.VISIBLE
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        rootView.progressBar.visibility = View.GONE
                    }
                }
            }
            .also { it.loadUrl(url) }

        return rootView
    }

    companion object {

        const val ARG_URL = "url"
    }
}

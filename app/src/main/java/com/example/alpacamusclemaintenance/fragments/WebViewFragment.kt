package com.example.alpacamusclemaintenance.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.alpacamusclemaintenance.R

/**
 * A simple [Fragment] subclass.
 * Use the [WebViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WebViewFragment : Fragment() {
    private var mWebView: WebView? = null
    private var mIsWebViewAvailable: Boolean = false

    /**
     * Gets the WebView.
     */
    val webView: WebView?
        get() = if (mIsWebViewAvailable) mWebView else null

    /**
     * Called to instantiate the view. Creates and returns the WebView.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_web_view, container, false)

        mWebView = rootView.findViewById(R.id.webView)
        mIsWebViewAvailable = true

        val progressBar: ProgressBar = rootView.findViewById(R.id.progressBar)
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }

        mWebView!!.loadUrl("https://github.com/alpaca0984/AlpacaMuscleMaintenance")

        return rootView
    }
}

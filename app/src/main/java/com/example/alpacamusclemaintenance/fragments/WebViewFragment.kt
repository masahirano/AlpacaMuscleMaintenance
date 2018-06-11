package com.example.alpacamusclemaintenance.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.alpacamusclemaintenance.R
import kotlinx.android.synthetic.main.fragment_web_view.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [WebViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WebViewFragment : Fragment() {
    private var webView: WebView? = null
        get() = if (isWebViewAvailable) field else null
    private var isWebViewAvailable: Boolean = false

    /**
     * Called to instantiate the view. Creates and returns the WebView.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_web_view, container, false)

        webView = rootView.webView
        isWebViewAvailable = true

        webView!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                rootView.progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                rootView.progressBar.visibility = View.GONE
            }
        }

        webView!!.loadUrl("https://github.com/alpaca0984/AlpacaMuscleMaintenance")

        return rootView

    }
}
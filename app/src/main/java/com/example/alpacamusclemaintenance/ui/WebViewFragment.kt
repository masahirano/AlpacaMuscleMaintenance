package com.example.alpacamusclemaintenance.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.alpacamusclemaintenance.R
import kotlinx.android.synthetic.main.fragment_web_view.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [WebViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WebViewFragment : Fragment() {
  private lateinit var url: String
  private var webView: WebView? = null
    get() = if (isWebViewAvailable) field else null
  private var isWebViewAvailable: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      url = it.getString(ARG_URL)!!
    }
  }

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

    webView!!.loadUrl(url)

    return rootView
  }

  companion object {

    const val ARG_URL = "url"

    @JvmStatic
    fun newInstance(url: String) =
      WebViewFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_URL, url)
        }
      }
  }
}

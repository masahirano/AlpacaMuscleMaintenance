package com.example.alpacamusclemaintenance.presentation

import android.content.Context
import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex

@Composable
fun WebViewView(
    url: String,
    context: Context = LocalContext.current
) {
    var isLoading by remember { mutableStateOf(true) }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    AndroidView(
        factory = {
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(
                        view: WebView?,
                        url: String?,
                        favicon: Bitmap?
                    ) {
                        super.onPageStarted(view, url, favicon)
                        isLoading = true
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        isLoading = false
                    }
                }

                loadUrl(url)
            }
        }
    )
}

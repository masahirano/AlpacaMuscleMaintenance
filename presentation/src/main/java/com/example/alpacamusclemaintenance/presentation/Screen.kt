package com.example.alpacamusclemaintenance.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

internal const val WEB_VIEW_ROUTE = "webview"

internal fun linkToWebView(url: String): String {
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    return "$WEB_VIEW_ROUTE/$encodedUrl"
}

internal sealed class Screen(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int
) {

    object Home : Screen(
        route = "home",
        iconId = R.drawable.ic_home_white_24dp,
        titleId = R.string.title_home
    )

    object Exercice : Screen(
        route = "exercice",
        iconId = R.drawable.ic_directions_run_white_24dp,
        titleId = R.string.title_exercise
    )

    object Record : Screen(
        route = "record",
        iconId = R.drawable.ic_graphic_eq_white_24dp,
        titleId = R.string.title_record
    )

    object Feed : Screen(
        route = "feed",
        iconId = R.drawable.ic_library_books_white_24dp,
        titleId = R.string.title_feed
    )

    object BugReport : Screen(
        route = linkToWebView("https://github.com/alpaca0984/AlpacaMuscleMaintenance"),
        iconId = R.drawable.ic_bug_report_white_24dp,
        titleId = R.string.title_bug_report
    )
}

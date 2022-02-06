package com.example.alpacamusclemaintenance.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.alpacamusclemaintenance.presentation.excercise.ExerciseView
import com.example.alpacamusclemaintenance.presentation.feed.FeedView
import com.example.alpacamusclemaintenance.presentation.home.HomeView
import com.example.alpacamusclemaintenance.presentation.record.RecordView
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    val items = listOf(
                        Screen.Home,
                        Screen.Exercice,
                        Screen.Record,
                        Screen.Feed
                    )
                    BottomNavigation {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        items.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(screen.iconId),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(screen.titleId)) },
                                selected = currentDestination
                                    ?.hierarchy
                                    ?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Home.route) {
                        HomeView()
                    }
                    composable(Screen.Exercice.route) {
                        ExerciseView()
                    }
                    composable(Screen.Record.route) {
                        RecordView()
                    }
                    composable(Screen.Feed.route) {
                        FeedView(navController)
                    }
                    composable("${Screen.WebView.route}/{encodedUrl}") { backStackEntry ->
                        val encodedUrl = backStackEntry.arguments?.getString("encodedUrl").orEmpty()
                        val url = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
                        WebViewView(url = url)
                    }
                }
            }
        }
    }
}

sealed class Screen(
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
        route = "bugreport",
        iconId = R.drawable.ic_bug_report_white_24dp,
        titleId = R.string.title_bug_report
    )
    object WebView : Screen(
        route = "webview",
        iconId = -1,
        titleId = -1
    )
}

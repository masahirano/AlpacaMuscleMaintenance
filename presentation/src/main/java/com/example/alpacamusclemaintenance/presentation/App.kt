package com.example.alpacamusclemaintenance.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.alpacamusclemaintenance.presentation.excercise.ExerciseView
import com.example.alpacamusclemaintenance.presentation.feed.FeedView
import com.example.alpacamusclemaintenance.presentation.home.HomeView
import com.example.alpacamusclemaintenance.presentation.record.RecordView
import com.google.accompanist.pager.ExperimentalPagerApi
import timber.log.Timber
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
internal fun App() {
    val navController: NavHostController = rememberNavController()
    val startScreen: Screen = Screen.Home
    var currentScreen: Screen by remember { mutableStateOf(startScreen) }

    Scaffold(
        topBar = { TopBar(title = stringResource(currentScreen.titleId)) },
        bottomBar = {
            BottomBar(
                navController = navController,
                onClick = { screen ->
                    currentScreen = screen
                }
            )
        }
    ) { innerPadding ->
        Navigation(
            navController = navController,
            startScreen = startScreen,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun TopBar(title: String) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(
                onClick = { Timber.d("Menu is clicked") }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    onClick: (Screen) -> Unit
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val items = listOf(
            Screen.Home,
            Screen.Exercice,
            Screen.Record,
            Screen.Feed,
            Screen.BugReport
        )
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
                        // @see https://developer.android.com/jetpack/compose/navigation#bottom-nav
                        //
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
                    onClick(screen)
                }
            )
        }
    }
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
private fun Navigation(
    navController: NavHostController,
    startScreen: Screen,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startScreen.route,
        modifier = modifier
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
        composable("$WEB_VIEW_ROUTE/{encodedUrl}") { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("encodedUrl").orEmpty()
            val url = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
            WebViewView(url = url)
        }
    }
}

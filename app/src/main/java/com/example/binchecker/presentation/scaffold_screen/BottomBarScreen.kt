package com.example.binchecker.presentation.scaffold_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.binchecker.R
import com.example.binchecker.presentation.navigation.AppNavGraph

sealed class BottomHomeScreenNavigation(val title: String, val iconId: Int, val route: String) {
    data object BinFindInfoScreen :
        BottomHomeScreenNavigation("Найти по BIN", R.drawable.search_loop_icon, "BinFindInfoScreen")

    data object BinHistoryScreen :
        BottomHomeScreenNavigation("История", R.drawable.history_icon, "BinHistoryScreen")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBarScreen(navController)
        }
    ) {
        AppNavGraph(navController)
    }
}

@Composable
fun BottomBarScreen(navController: NavController) {
    val listBottomNavigationItems = listOf(
        BottomHomeScreenNavigation.BinFindInfoScreen,
        BottomHomeScreenNavigation.BinHistoryScreen
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        listBottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier
                    .height(50.dp),
                selected = currentRoute == item.route,
                onClick = {
                    val route = item.route
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(painter = painterResource(id = item.iconId), contentDescription = "Icon", modifier = Modifier.size(30.dp))
                },
                label = {
                    Text(
                        text = item.title,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                },
                selectedContentColor = colorResource(id = R.color.author),
                unselectedContentColor = Color.Gray
            )
        }

    }
}
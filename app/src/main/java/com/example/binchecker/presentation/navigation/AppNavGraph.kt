package com.example.binchecker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.binchecker.presentation.bin_find_info.BinFindInfoScreen
import com.example.binchecker.presentation.bin_find_info.BinFindInfoViewModel
import com.example.binchecker.presentation.bin_history.BinHistoryScreen
import com.example.binchecker.presentation.bin_history.BinHistoryViewModel
import com.example.binchecker.presentation.scaffold_screen.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(navController, startDestination = "BinFindInfoScreen") {
        composable("BottomBarScreen") {
            HomeScreen()
        }
        composable("BinFindInfoScreen") {
            val binFindInfoViewModel: BinFindInfoViewModel = hiltViewModel()
            BinFindInfoScreen(binFindInfoViewModel)
        }
        composable("BinHistoryScreen") {
            val binHistoryViewModel: BinHistoryViewModel = hiltViewModel()
            BinHistoryScreen(binHistoryViewModel)
        }
    }

}
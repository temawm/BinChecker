package com.example.binchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.binchecker.presentation.bin_find_info.BinFindInfoScreen
import com.example.binchecker.presentation.navigation.AppNavGraph
import com.example.binchecker.presentation.scaffold_screen.HomeScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen()
            }
        }
    }

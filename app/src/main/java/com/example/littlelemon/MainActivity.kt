package com.example.littlelemon

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.utils.SHARED_PREF_NAME

class MainActivity : ComponentActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LittleLemonTheme {
                Navigation(navController = navController)
            }
        }
    }
}
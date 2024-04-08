package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.ui.screens.Home
import com.example.littlelemon.ui.screens.Onboarding
import com.example.littlelemon.ui.screens.Profile
import com.example.littlelemon.utils.SHARED_PREF_NAME
import com.example.littlelemon.utils.getUser

@Composable
fun Navigation(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = getStartDestination(context)) {
        composable(HomeDest.route) {
            Home(navController)
        }
        composable(OnboardingDest.route) {
            Onboarding(navController)
        }
        composable(ProfileDest.route) {
            Profile(navController)
        }
    }
}

fun getStartDestination(context: Context): String {
    val userData = getUser(context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE))
    return if (userData != null) HomeDest.route else OnboardingDest.route
}
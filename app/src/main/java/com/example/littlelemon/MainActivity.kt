package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val API_URL =
    "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val menuItemDao = AppDatabase.getDatabase(this).menuItemDao()
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val menuItems = getMenu()
            withContext(IO) {
                menuItemDao.saveAll(menuItems.menu.map { it.toMenuItem() }.toList())
            }
        }
        setContent {
            val navController = rememberNavController()
            LittleLemonTheme {
                Navigation(navController = navController)
            }
        }
    }

    private suspend fun getMenu(): MenuNetworkData {
        val httpClient = HttpClient(Android) {
            install(ContentNegotiation) {
                json(contentType = ContentType("text", "plain"))
            }
        }
        return httpClient.get(API_URL).body<MenuNetworkData>()
    }
}
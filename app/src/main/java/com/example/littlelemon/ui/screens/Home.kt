package com.example.littlelemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.ProfileDest
import com.example.littlelemon.R
import com.example.littlelemon.ui.composables.MenuItems
import com.example.littlelemon.ui.theme.Primary1
import com.example.littlelemon.ui.theme.Primary2
import com.example.littlelemon.ui.theme.Secondary4
import com.example.littlelemon.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    val dao = AppDatabase.getDatabase(LocalContext.current).menuItemDao()
    val menuItemsLiveData by dao.loadAllMenuItems().observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        alignment = Alignment.Center,
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(5.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(ProfileDest.route)
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary1)
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = stringResource(id = R.string.restaurant_name),
                        style = Typography.titleLarge,
                        color = Primary2,
                    )
                    Text(
                        text = stringResource(id = R.string.city),
                        color = Color.White,
                        style = Typography.titleMedium
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.short_description),
                            color = Color.White,
                            style = Typography.displayMedium,
                            modifier = Modifier.width(240.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.hero),
                            contentDescription = "Hero image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .height(150.dp),
                        )
                    }
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.search_placeholder),
                                color = Secondary4
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search icon"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    )
                }
            }
            MenuItems(menuItems = menuItemsLiveData ?: listOf())
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    Home(rememberNavController())
}
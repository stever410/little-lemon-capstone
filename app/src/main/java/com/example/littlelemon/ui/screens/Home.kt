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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.MenuItem
import com.example.littlelemon.ProfileDest
import com.example.littlelemon.R
import com.example.littlelemon.ui.composables.MenuItems
import com.example.littlelemon.ui.theme.Primary1
import com.example.littlelemon.ui.theme.Primary2
import com.example.littlelemon.ui.theme.Secondary3
import com.example.littlelemon.ui.theme.Secondary4
import com.example.littlelemon.ui.theme.Typography
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    val focusManager = LocalFocusManager.current
    val dao = AppDatabase.getDatabase(LocalContext.current).menuItemDao()
    val menuItemsLiveData by dao.loadAllMenuItems().observeAsState()
    var searchPhrase by remember { mutableStateOf("") }
    var categorySelection by remember { mutableStateOf("") }

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
                        value = searchPhrase,
                        onValueChange = { searchPhrase = it },
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
                            .background(Color.White),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() })
                    )
                }
            }
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = stringResource(R.string.order_for_delivery),
                    style = Typography.headlineMedium,
                )
                LazyRow() {
                    val categories = getCategories(menuItemsLiveData)
                    items(categories.size) {
                        FilterChip(
                            selected = categorySelection.equals(categories[it], ignoreCase = true),
                            onClick = {
                                categorySelection = if (categorySelection != categories[it])
                                    categories[it] else ""
                            },
                            label = {
                                Text(categories[it], style = Typography.labelSmall)
                            },
                            modifier = Modifier.padding(end = 10.dp),
                            border = FilterChipDefaults.filterChipBorder(
                                borderColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(16.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Secondary3,
                                labelColor = Secondary4
                            )
                        )
                    }
                }
                Divider()
                MenuItems(
                    menuItems = filterMenuItems(
                        menuItemsLiveData,
                        searchPhrase,
                        categorySelection
                    )
                )
            }
        }
    }
}

fun filterMenuItems(
    menuItems: List<MenuItem>?,
    searchPhrase: String,
    category: String
): List<MenuItem> {
    return menuItems?.filter {
        it.title.contains(searchPhrase, ignoreCase = true)
                && (category.isEmpty() || it.category.equals(category, ignoreCase = true))
    } ?: emptyList()
}

fun getCategories(menuItems: List<MenuItem>?): List<String> {
    return menuItems?.map { it ->
        it.category.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }?.distinct() ?: emptyList()
}

@Composable
@Preview
fun HomePreview() {
    Home(rememberNavController())
}
package com.example.littlelemon.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuItem
import com.example.littlelemon.ui.theme.Typography

@Composable
fun MenuItems(menuItems: List<MenuItem>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(count = menuItems.size) {
            Menu(menuItem = menuItems[it])
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Menu(menuItem: MenuItem) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = menuItem.title, style = Typography.titleSmall)
            Text(text = menuItem.description, style = Typography.displaySmall)
            Text(text = "$${menuItem.price}", style = Typography.headlineSmall)
        }
        GlideImage(
            model = menuItem.image,
            contentDescription = menuItem.title,
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
@Preview
fun MenuItemsPreview() {
    MenuItems(
        listOf(
            MenuItem(
                1,
                "Test",
                "Test",
                10.12,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
                "Starter"
            )
        )
    )
}
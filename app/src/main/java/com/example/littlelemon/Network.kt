package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("MenuNetworkData")
data class MenuNetworkData(
    val menu: List<MenuItemNetwork>
)

@Serializable
@SerialName("MenuItemNetwork")
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
) {
    fun toMenuItem(): MenuItem = MenuItem(
        id,
        title,
        description,
        price,
        image,
        category
    )
}
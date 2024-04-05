package com.example.littlelemon.data

import java.io.Serializable

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
) : Serializable
package com.example.littlelemon.utils

import android.content.SharedPreferences
import com.example.littlelemon.data.User
import com.google.gson.Gson

const val SHARED_PREF_NAME = "my_little_lemon"
const val USER_KEY = "user"
val gson = Gson()

fun getUser(sharedPreferences: SharedPreferences): User? {
    val userJson = sharedPreferences.getString(USER_KEY, "")
    return if (userJson != null) gson.fromJson(userJson, User::class.java) else null
}

fun saveUser(sharedPreferences: SharedPreferences, user: User) {
    val userJson = gson.toJson(user)
    with(sharedPreferences.edit()) {
        putString(USER_KEY, userJson)
        apply()
    }
}

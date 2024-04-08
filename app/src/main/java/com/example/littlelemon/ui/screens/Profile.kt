package com.example.littlelemon.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.OnboardingDest
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Primary2
import com.example.littlelemon.utils.SHARED_PREF_NAME
import com.example.littlelemon.utils.clearData
import com.example.littlelemon.utils.getUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController) {
    val sharedPreferences =
        LocalContext.current.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val user = getUser(sharedPreferences)

    Scaffold(
        topBar = {
            TopAppBar(title = {
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
            })
        },
        bottomBar = {
            BottomAppBar(containerColor = Color.Transparent) {
                Button(
                    onClick = {
                        clearData(sharedPreferences)
                        navController.navigate(OnboardingDest.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary2,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = stringResource(id = R.string.logout))
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 40.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = stringResource(id = R.string.profile_information),
                    fontSize = 24.sp
                )
                OutlinedTextField(
                    value = user?.firstName ?: "",
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.first_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    readOnly = true
                )
                OutlinedTextField(
                    value = user?.lastName ?: "",
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.last_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    readOnly = true
                )
                OutlinedTextField(
                    value = user?.email ?: "",
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.email)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
            }
        }
    }
}

@Composable
@Preview
fun ProfilePreview() {
    Profile(rememberNavController())
}
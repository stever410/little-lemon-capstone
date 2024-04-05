package com.example.littlelemon.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.HomeDest
import com.example.littlelemon.R
import com.example.littlelemon.data.User
import com.example.littlelemon.ui.theme.Primary1
import com.example.littlelemon.ui.theme.Primary2
import com.example.littlelemon.utils.SHARED_PREF_NAME
import com.example.littlelemon.utils.saveUser
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val errorMessage = stringResource(id = R.string.failed_registration)
    val successMessage = stringResource(id = R.string.successful_registration)

    fun isInvalidInputs(): Boolean = firstName.isBlank() || lastName.isBlank() || email.isBlank()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
                        if (isInvalidInputs()) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = errorMessage,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = successMessage,
                                    duration = SnackbarDuration.Short
                                )
                            }
                            saveUser(
                                sharedPreferences = context.getSharedPreferences(
                                    SHARED_PREF_NAME,
                                    Context.MODE_PRIVATE
                                ),
                                user = User(firstName, lastName, email)
                            )
                            navController.navigate(HomeDest.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary2,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = stringResource(id = R.string.register))
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = stringResource(id = R.string.onboarding_text),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary1, RectangleShape)
                    .padding(40.dp)
            )
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
                Text(text = stringResource(id = R.string.personal_information), fontSize = 24.sp)
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { newValue -> firstName = newValue },
                    label = { Text(stringResource(id = R.string.first_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { newValue -> lastName = newValue },
                    label = { Text(stringResource(id = R.string.last_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { newValue -> email = newValue },
                    label = { Text(stringResource(id = R.string.email)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
@Preview
fun OnboardingPreview() {
    Onboarding(rememberNavController())
}
package com.example.plantdiseasedetector

import android.widget.Toast
import com.example.plantdiseasedetector.desing_system.AuthOption
import com.example.plantdiseasedetector.desing_system.MyTextField
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    val emailState = remember { TextFieldState() }
    val passwordState = remember { TextFieldState() }
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.register),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth(0.25f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Register",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AuthOption(image = R.drawable.google)
            AuthOption(image = R.drawable.facebook)
        }

        Text(
            text = "Or, login with...",
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(0.5f)
        )

        MyTextField(
            textFieldState = emailState,
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = Icons.Outlined.Check,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            textFieldState = passwordState,
            hint = "Name",
            leadingIcon = Icons.Outlined.AccountCircle,
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            textFieldState = TextFieldState(),
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val email = emailState.text.toString().trim()
                val password = passwordState.text.toString().trim()
                if (email.isEmpty() || password.isEmpty()) {
                    // Show error message
                    return@Button
                }
                authViewModel.register(
                    email = emailState.text.toString(),
                    password = passwordState.text.toString()
                ) { success, message ->
                    if (success) {
                        Toast.makeText(context, "Registration successful, please log in.", Toast.LENGTH_LONG).show()
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Register",
                fontSize = 17.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }


        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Already have an account? ",
                fontSize = 16.sp,
            )
            Text(
                text = "Login",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }

        Spacer(modifier = Modifier.height(1.dp))
    }
}
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val emailState = remember { TextFieldState() }
    val passwordState = remember { TextFieldState() }
    val authViewModel: AuthViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.login),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth(0.25f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Login",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        }

        MyTextField(
            textFieldState = emailState,  // Rename from emailState
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = Icons.Outlined.Check,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            textFieldState = passwordState,
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            trailingText = "Forgot?",
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val email = emailState.text.toString().trim()
                val password = passwordState.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    // Show error message (Snackbar or Toast)
                    return@Button
                }
                authViewModel.login(
                    email = emailState.text.toString(),
                    password = passwordState.text.toString()
                ) { success, message ->
                    if (success) {
                        navController.navigate("main") { popUpTo("login") }
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login",
                fontSize = 17.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Text(
            text = "Or, login with...",
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(0.5f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AuthOption(image = R.drawable.google)
            AuthOption(image = R.drawable.facebook)
        }

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Don't have an account? ",
                fontSize = 16.sp,
            )
            Text(
                text = "Register",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )
        }

        Spacer(modifier = Modifier.height(1.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController =
    rememberNavController())
}
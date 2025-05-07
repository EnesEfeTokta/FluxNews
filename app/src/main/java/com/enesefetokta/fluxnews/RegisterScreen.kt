package com.enesefetokta.fluxnews.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesefetokta.fluxnews.R
import com.enesefetokta.fluxnews.ui.theme.FluxNewsTheme

@Composable
fun VisualRegisterScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onRegisterClick: (String, String, String, String) -> Unit = { _, _, _, _ -> },
    onLoginInsteadClick: () -> Unit = {}
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val curveShape = remember { InverseArcShape(arcHeightDp = 40f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.42f)
                .background(Color.White)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.72f)
                .graphicsLayer {
                    shape = curveShape
                    clip = true
                }
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 80.dp), // Dikey padding daha az olabilir
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val textFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                    disabledTextColor = Color.Gray, focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent, disabledContainerColor = Color.Transparent,
                    cursorColor = Color.White, focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Gray, disabledIndicatorColor = Color.DarkGray,
                    focusedLabelColor = Color.Gray, unfocusedLabelColor = Color.Gray,
                    disabledLabelColor = Color.DarkGray
                )

                // Full Name
                TextField(
                    value = fullName, onValueChange = { fullName = it },
                    label = { Text("Full Name") }, singleLine = true,
                    colors = textFieldColors, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Email
                TextField(
                    value = email, onValueChange = { email = it },
                    label = { Text("Email") }, singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = textFieldColors, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Password
                TextField(
                    value = password, onValueChange = { password = it },
                    label = { Text("Password") }, singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description, tint = Color.Gray)
                        }
                    },
                    colors = textFieldColors, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password
                TextField(
                    value = confirmPassword, onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") }, singleLine = true,
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (confirmPasswordVisible) "Hide password" else "Show password"
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(imageVector = image, description, tint = Color.Gray)
                        }
                    },
                    colors = textFieldColors, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Or register with", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SocialIconButton(R.drawable.ic_google_logo, "Google Register") { /* Google Register */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_facebook_logo, "Facebook Register") { /* Facebook Register */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_github_logo, "GitHub Register") { /* GitHub Register */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_linkedin_logo, "LinkedIn Register") { /* LinkedIn Register */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_x_logo, "X Register") { /* X Register */ }
                }
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onRegisterClick(fullName, email, password, confirmPassword) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(0.6f).height(50.dp)
                ) {
                    Text("Register", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = onLoginInsteadClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Already have an account? Login",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Geri",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Register",
                fontSize = 65.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true, name = "Visual Register Light Preview")
@Composable
fun VisualRegisterScreenPreview() {
    FluxNewsTheme {
        VisualRegisterScreen(
            onBackClick = {},
            onRegisterClick = { _, _, _, _ -> },
            onLoginInsteadClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Visual Register Dark Preview")
@Composable
fun VisualRegisterScreenDarkPreview() {
    FluxNewsTheme(darkTheme = true) {
        VisualRegisterScreen(
            onBackClick = {},
            onRegisterClick = { _, _, _, _ -> },
            onLoginInsteadClick = {}
        )
    }
}
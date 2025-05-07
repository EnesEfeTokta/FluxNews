package com.enesefetokta.fluxnews.ui

import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextAlign
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
import com.enesefetokta.fluxnews.ui.theme.FluxNewsTheme

@Composable
fun VisualNewPasswordScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSetNewPasswordClick: (String, String) -> Unit = { _, _ -> }
) {
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmNewPassword by rememberSaveable { mutableStateOf("") }
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmNewPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val curveShape = remember { InverseArcShape(arcHeightDp = 40f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .background(Color.White)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.70f)
                .graphicsLayer {
                    shape = curveShape
                    clip = true
                }
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp,
                        vertical = 130.dp),
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

                // Açıklama Metni
                Text(
                    text = "Create a new password for your account. Make sure it's strong and memorable.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // New Password
                TextField(
                    value = newPassword, onValueChange = { newPassword = it },
                    label = { Text("New Password") }, singleLine = true,
                    visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (newPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                            Icon(imageVector = image, "Toggle password visibility", tint = Color.Gray)
                        }
                    },
                    colors = textFieldColors, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Confirm New Password
                TextField(
                    value = confirmNewPassword, onValueChange = { confirmNewPassword = it },
                    label = { Text("Confirm New Password") }, singleLine = true,
                    visualTransformation = if (confirmNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (confirmNewPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { confirmNewPasswordVisible = !confirmNewPasswordVisible }) {
                            Icon(imageVector = image, "Toggle password visibility", tint = Color.Gray)
                        }
                    },
                    colors = textFieldColors, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))

                // "Set New Password" Butonu
                Button(
                    onClick = { onSetNewPasswordClick(newPassword, confirmNewPassword) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f).height(50.dp)
                ) {
                    Text("Set New Password", fontSize = 16.sp)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            IconButton(onClick = onBackClick, enabled = false) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Geri",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Set New Password",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true, name = "Visual New Password Light Preview")
@Composable
fun VisualNewPasswordScreenPreview() {
    FluxNewsTheme {
        VisualNewPasswordScreen(
            onBackClick = {},
            onSetNewPasswordClick = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Visual New Password Dark Preview")
@Composable
fun VisualNewPasswordScreenDarkPreview() {
    FluxNewsTheme(darkTheme = true) {
        VisualNewPasswordScreen(
            onBackClick = {},
            onSetNewPasswordClick = { _, _ -> }
        )
    }
}
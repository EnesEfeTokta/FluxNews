package com.enesefetokta.fluxnews.ui

import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesefetokta.fluxnews.ui.theme.FluxNewsTheme

@Composable
fun VisualForgotPasswordScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSendResetLinkClick: (String) -> Unit = { _ -> },
    onLoginInsteadClick: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }

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
                        vertical = 200.dp),
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
                    text = "Enter your email address and we'll send you a link to reset your password.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center, // Metni ortala
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Email
                TextField(
                    value = email, onValueChange = { email = it },
                    label = { Text("Email") }, singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = textFieldColors, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onSendResetLinkClick(email) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f).height(50.dp)
                ) {
                    Text("Send Reset Code", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(24.dp))

                TextButton(
                    onClick = onLoginInsteadClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Remembered your password? Login",
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
                text = "Forgot Password",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true, name = "Visual Forgot Password Light Preview")
@Composable
fun VisualForgotPasswordScreenPreview() {
    FluxNewsTheme {
        VisualForgotPasswordScreen(
            onBackClick = {},
            onSendResetLinkClick = { _ -> },
            onLoginInsteadClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Visual Forgot Password Dark Preview")
@Composable
fun VisualForgotPasswordScreenDarkPreview() {
    FluxNewsTheme(darkTheme = true) {
        VisualForgotPasswordScreen(
            onBackClick = {},
            onSendResetLinkClick = { _ -> },
            onLoginInsteadClick = {}
        )
    }
}
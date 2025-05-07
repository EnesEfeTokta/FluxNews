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
fun VisualOtpVerificationScreen(
    modifier: Modifier = Modifier,
    emailForInfo: String,
    onBackClick: () -> Unit = {},
    onVerifyCodeClick: (String) -> Unit = { _ -> }, // otpCode
    onResendCodeClick: () -> Unit = {},
    onLoginInsteadClick: () -> Unit = {}
) {
    var otpCode by rememberSaveable { mutableStateOf("") }

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
                        vertical = 150.dp),
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
                    text = "We've sent a verification code to\n$emailForInfo.\nPlease enter it below.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // OTP Code Input
                TextField(
                    value = otpCode,
                    onValueChange = { if (it.length <= 6) otpCode = it },
                    label = { Text("Verification Code") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    colors = textFieldColors,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onVerifyCodeClick(otpCode) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(0.7f).height(50.dp)
                ) {
                    Text("Verify Code", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = onResendCodeClick) {
                        Text("Resend Code", color = Color.Gray, fontSize = 12.sp)
                    }
                    TextButton(onClick = onLoginInsteadClick) {
                        Text("Back to Login", color = Color.Gray, fontSize = 12.sp)
                    }
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
                text = "Verify Email", // Başlık
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true, name = "Visual OTP Verification Light Preview")
@Composable
fun VisualOtpVerificationScreenPreview() {
    FluxNewsTheme {
        VisualOtpVerificationScreen(
            emailForInfo = "test@example.com",
            onBackClick = {},
            onVerifyCodeClick = { _ -> },
            onResendCodeClick = {},
            onLoginInsteadClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Visual OTP Verification Dark Preview")
@Composable
fun VisualOtpVerificationScreenDarkPreview() {
    FluxNewsTheme(darkTheme = true) {
        VisualOtpVerificationScreen(
            emailForInfo = "test@example.com",
            onBackClick = {},
            onVerifyCodeClick = { _ -> },
            onResendCodeClick = {},
            onLoginInsteadClick = {}
        )
    }
}
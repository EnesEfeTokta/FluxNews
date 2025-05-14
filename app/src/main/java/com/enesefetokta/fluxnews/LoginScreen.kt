package com.enesefetokta.fluxnews.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
// import androidx.compose.ui.graphics.vector.ImageVector // Kullanılmıyorsa kaldırılabilir
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
// import androidx.compose.ui.text.TextStyle // Kullanılmıyorsa kaldırılabilir
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesefetokta.fluxnews.R
import com.enesefetokta.fluxnews.ui.theme.FluxNewsTheme

@Composable
fun VisualLoginScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {} // Yeni tıklama işlevi
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    // Kavis yüksekliğini ayarlayabilirsiniz, 40f fena değil gibi görünüyor.
    val curveShape = remember { InverseArcShape(arcHeightDp = 40f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.48f)
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
                    .padding(horizontal = 32.dp, vertical = 100.dp),
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

                // Username / Email
                TextField(
                    value = username, onValueChange = { username = it },
                    label = { Text("Username/Email") }, singleLine = true,
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

                // Forgot Password
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = onForgotPasswordClick,
                    modifier = Modifier.align(Alignment.End),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Forgot Password?",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "With Other Session Tools", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SocialIconButton(R.drawable.ic_google_logo, "Google Login") { /* Google Login */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_facebook_logo, "Facebook Login") { /* Facebook Login */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_x_logo, "X Login") { /* X Login */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_github_logo, "GitHub Login") { /* GitHub Login */ }
                    Spacer(modifier = Modifier.width(16.dp))
                    SocialIconButton(R.drawable.ic_linkedin_logo, "LinkedIn Login") { /* LinkedIn Login */ }
                }
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onLoginClick(username, password) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(0.6f).height(50.dp)
                ) {
                    Text("Let's Go", fontSize = 16.sp)
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
                text = "Login",
                fontSize = 65.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun SocialIconButton(
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true, name = "Visual Login Light Preview")
@Composable
fun VisualLoginScreenPreview() {
    FluxNewsTheme {
        VisualLoginScreen(
            onBackClick = {},
            onLoginClick = { _, _ -> },
            onForgotPasswordClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Visual Login Dark Preview")
@Composable
fun VisualLoginScreenDarkPreview() {
    FluxNewsTheme(darkTheme = true) {
        VisualLoginScreen(
            onBackClick = {},
            onLoginClick = { _, _ -> },
            onForgotPasswordClick = {}
        )
    }
}
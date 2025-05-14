package com.enesefetokta.fluxnews.ui // Paket adınızı kendi paket adınızla değiştirin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesefetokta.fluxnews.R // Kendi R dosyanızın yolu
import com.enesefetokta.fluxnews.ui.theme.FluxNewsTheme

// InverseArcShape sınıfınız (önceki koddan)
class InverseArcShape(private val arcHeightDp: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val arcHeight = with(density) { arcHeightDp.dp.toPx() }
        val path = Path().apply {
            moveTo(0f, arcHeight) // Başlangıç noktasını kavisin başladığı yere ayarla
            quadraticBezierTo(
                x1 = size.width / 2f, y1 = -arcHeight / 2, // Kavisin tepe noktası (y1'i biraz azalttım)
                x2 = size.width, y2 = arcHeight  // Bitiş noktası
            )
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun VisualWelcomeScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    val curveShape = remember { InverseArcShape(arcHeightDp = 70f) } // Kavis yüksekliğini ayarlayın

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White) // Ana arka plan beyaz
    ) {
        // Üst Kısım (WELCOME, ikon, açıklama, noktalar)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f) // Ekranın yaklaşık %75'i
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WELCOME",
                fontSize = 48.sp, // Font boyutunu ayarlayın
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_fluxnews_logo), // Kendi ikonunuzu kullanın
                contentDescription = "FluxNews Logo",
                modifier = Modifier.size(120.dp), // İkon boyutunu ayarlayın
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "The place for clean news.",
                fontSize = 16.sp,
                color = Color.DarkGray, // Rengi görseldeki gibi ayarlayın
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Sayfa Gösterge Noktaları (Pager Dots)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Aktif nokta
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.Black, CircleShape)
                )
                // Pasif noktalar (4 adet)
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color.LightGray, CircleShape)
                    )
                }
            }
        }

        // Alt Kısım (Kavisli siyah alan ve butonlar)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.35f) // Ekranın yaklaşık %35'i (biraz üst üste binebilir)
                .graphicsLayer {
                    shape = curveShape
                    clip = true
                }
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top, // Butonları yukarıya yakınlaştırmak için
                modifier = Modifier.padding(top = (70f / 2).dp + 20.dp) // Kavisin yarısı + ekstra boşluk
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Login Butonu
                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray, // Görseldeki gibi açık gri
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Login", fontSize = 16.sp)
                    }

                    // Register Butonu
                    Button(
                        onClick = onRegisterClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.DarkGray, // Görseldeki gibi koyu gri
                            contentColor = Color.White
                        )
                    ) {
                        Text("Register", fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = onForgotPasswordClick) {
                    Text(
                        text = "Forgot?",
                        color = Color.Gray, // Görseldeki gibi gri
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "Welcome Screen Light Preview")
@Composable
fun WelcomeScreenPreview() {
    FluxNewsTheme {
        WelcomeScreen(
            onLoginClick = {},
            onRegisterClick = {},
            onForgotPasswordClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Welcome Screen Dark Preview (Not relevant for this design)")
@Composable
fun WelcomeScreenDarkPreview() {
    // Bu tasarımda karanlık modun pek bir anlamı yok çünkü renkler sabit.
    // Ancak tema uygulamasını görmek için kalabilir.
    FluxNewsTheme(darkTheme = true) {
        Surface { // Karanlık modda Surface kullanmak iyi bir pratiktir
            WelcomeScreen(
                onLoginClick = {},
                onRegisterClick = {},
                onForgotPasswordClick = {}
            )
        }
    }
}
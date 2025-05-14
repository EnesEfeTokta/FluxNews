package com.enesefetokta.fluxnews.ui // Kendi paket adınızı kullanın

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager // M3 Pager
import androidx.compose.foundation.pager.rememberPagerState // M3 Pager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder // Veya Favorite
import androidx.compose.material.icons.filled.NotificationsNone // Veya Notifications
import androidx.compose.material.icons.filled.PersonOutline // Veya Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesefetokta.fluxnews.R // Drawable kaynakları için
import com.enesefetokta.fluxnews.ui.theme.FluxNewsTheme // Kendi temanız
import androidx.compose.ui.graphics.ColorFilter

// --- Veri Sınıfları ---
data class CategoryItem(
    val id: Int,
    val name: String,
    val icon: Int // Drawable resource ID
)

data class TrendItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: Int // Drawable resource ID
)

data class BottomNavItem(
    val label: String,
    val icon: @Composable () -> Unit, // İkonu Composable olarak alalım
    val route: String // Navigasyon için
)

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.foundation.ExperimentalFoundationApi::class) // Pager için
@Composable
fun VisualHomeScreen(
    modifier: Modifier = Modifier,
    username: String = "Enes Efe", // Dinamik olarak alınabilir
    onNotificationClick: () -> Unit = {},
    onCategoryClick: (CategoryItem) -> Unit = {},
    onTrendClick: (TrendItem) -> Unit = {},
    onViewAllTrendsClick: () -> Unit = {},
    onBottomNavClick: (String) -> Unit = {}
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val curveShape = remember { InverseArcShape(arcHeightDp = 20f) } // Kavis yüksekliği

    // Örnek Veriler
    val categories = remember {
        listOf(
            CategoryItem(1, "Sports", R.drawable.ic_placeholder_sports), // Kendi ikonunuzu ekleyin
            CategoryItem(2, "Science", R.drawable.ic_placeholder_science),
            CategoryItem(3, "Important", R.drawable.ic_placeholder_important),
            CategoryItem(4, "Economy", R.drawable.ic_placeholder_economy)
            // ... daha fazla kategori
        )
    }
    val trends = remember {
        listOf(
            TrendItem(1, "The Rise of AI", "OpenAI has announced a new model...", R.drawable.trend_ai),
            TrendItem(2, "Big Shock at UEFA", "Corruption case in UEFA's most prominent teams....", R.drawable.trend_uefa),
            TrendItem(3, "Market Volatility", "Experts discuss the recent market shifts.", R.drawable.trend_ai) // Placeholder
        )
    }
    val bottomNavItems = remember {
        listOf(
            BottomNavItem("Favorites", { Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorites") }, "favorites"),
            BottomNavItem("Profile", { Icon(Icons.Filled.PersonOutline, contentDescription = "Profile") }, "profile"),
            BottomNavItem("Settings", { Icon(Icons.Filled.Settings, contentDescription = "Settings") }, "settings")
        )
    }
    var selectedBottomNavItemIndex by rememberSaveable { mutableIntStateOf(0) } // Varsayılan olarak ilk item seçili

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF3A3A3A) // Görseldeki gibi koyu gri
            ) {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { item.icon() },
                        label = null, // Görselde label yok gibi, istenirse eklenebilir: { Text(item.label) }
                        selected = selectedBottomNavItemIndex == index,
                        onClick = {
                            selectedBottomNavItemIndex = index
                            onBottomNavClick(item.route)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White, // Seçili ikon rengi
                            unselectedIconColor = Color.LightGray, // Seçili olmayan ikon
                            indicatorColor = Color.Transparent // Seçili item arka planını şeffaf yap
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding) // Scaffold'un padding'ini uygula
                .background(Color.White) // Ana sayfa arka planı (trendler kısmı için)
        ) {
            // --- Üst Siyah Alan ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 30.dp) // Alt padding kavis için
            ) {
                // Selamlama ve Bildirim
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Good morning,",
                            color = Color.LightGray,
                            fontSize = 20.sp
                        )
                        Text(
                            text = username,
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(onClick = onNotificationClick) {
                        Icon(
                            Icons.Filled.NotificationsNone,
                            contentDescription = "Notifications",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Arama Çubuğu
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("News Search...", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon", tint = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.DarkGray,
                        focusedContainerColor = Color(0x33FFFFFF), // Hafif şeffaf beyaz
                        unfocusedContainerColor = Color(0x1AFFFFFF)
                    ),
                    singleLine = true
                )
            }

            // --- Kategoriler (Kavisli Beyaz Arka Planlı) ---
            Surface( // Veya Box
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer { // Kavisli kesim için
                        shape = curveShape
                        clip = true
                    }
                    .offset(y = (-20).dp), // Siyah alanın biraz üzerine bindir
                color = Color(0xFFF0F0F0) // Kategorilerin arka plan rengi (hafif gri)
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 20.dp), // Kavis ve içerik için padding
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(categories) { category ->
                        CategoryChip(category = category, onClick = { onCategoryClick(category) })
                    }
                }
            }

            // --- Trendler Bölümü ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp) // Kategori alanıyla boşluk ayarı
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Trends",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    TextButton(onClick = onViewAllTrendsClick) {
                        Text("View All", color = MaterialTheme.colorScheme.primary)
                    }
                }

                val pagerState = rememberPagerState(pageCount = { trends.size })
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp), // Kartlar arası boşluk için
                    pageSpacing = 12.dp // Sayfalar (kartlar) arası boşluk
                ) { page ->
                    TrendNewsCard(
                        trend = trends[page],
                        onClick = { onTrendClick(trends[page]) }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Pager Göstergesi
                Row(
                    Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryChip(category: CategoryItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(100.dp) // Sabit genişlik
            .height(120.dp) // Sabit yükseklik
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = category.icon), // Kendi ikonunuzu kullanın
                contentDescription = category.name,
                modifier = Modifier.size(48.dp),
                colorFilter = ColorFilter.tint(Color.DarkGray) // İkon rengi
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun TrendNewsCard(trend: TrendItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(280.dp) // Genişliği ayarlayın
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Kart arka planı
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = trend.imageUrl), // Kendi resminizi kullanın
                contentDescription = trend.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Resim yüksekliği
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = trend.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = trend.description,
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Go to News...",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}


@Preview(showBackground = true, name = "Home Screen Light")
@Composable
fun VisualHomeScreenPreview() {
    // Geçici drawable'lar eklediğinizi varsayarak
    // R.drawable.ic_placeholder_sports = Icons.Outlined.SportsSoccer (bu şekilde olmaz, XML olmalı)
    // R.drawable.trend_ai = R.drawable.ic_launcher_background (test için)
    // R.drawable.trend_uefa = R.drawable.ic_launcher_background (test için)
    // Bunları projenize uygun şekilde eklemelisiniz.
    FluxNewsTheme {
        VisualHomeScreen()
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Home Screen Dark")
@Composable
fun VisualHomeScreenDarkPreview() {
    FluxNewsTheme(darkTheme = true) {
        VisualHomeScreen()
    }
}
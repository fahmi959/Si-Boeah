package com.capstonebangkit.siboeah


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {

    val images = listOf(
        R.drawable.banner1, // Gambar untuk slide pertama
        R.drawable.banner2 // Gambar untuk slide kedua
    )
    val context = LocalContext.current
    var currentPage by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("https://caspstone-artikel-bix2qs6woa-et.a.run.app/") // Ganti dengan URL base dari API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService = retrofit.create(ApiService::class.java)

    var tipsList by remember { mutableStateOf<List<TipData>>(emptyList()) }




    LaunchedEffect(Unit) {
        try {
            val tips = apiService.getTips()
            tipsList = tips
        } catch (e: Exception) {
            // Handle error
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.weight(1f))
            BannerCard(images, currentPage, coroutineScope, context) { page ->
                currentPage = page
            }
            Spacer(Modifier.weight(1f))
            FeatureMenuCard(apiService, tipsList,)
            Spacer(Modifier.weight(1f))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Posisi Bottom Bar
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .drawWithContent {
                        val height = size.height
                        val shadowHeight = 8.dp.toPx()

                        drawContent()

                        drawRect(
                            color = Color.Black.copy(alpha = 0.12f),
                            topLeft = Offset(0f, height),
                            size = Size(size.width, shadowHeight),
                        )
                    }
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconMenuBottomBar(
                    icon = R.drawable.mi_home,
                    contentDescription = "Home",
                    text = "Home",
                    onClick = {
                        // Tindakan perpindahan aktivitas ke halaman Home
//                        val intent = Intent(context, MainActivity::class.java)
//                        context.startActivity(intent)
                    }
                )
                IconMenuBottomBar(
                    icon = R.drawable.mi_image,
                    contentDescription = "Gambar",
                    onClick = {
                        // Tindakan perpindahan aktivitas ke halaman Gambar
//                        val intent = Intent(context, MainActivity::class.java)
//                        context.startActivity(intent)
                    }
                )
                IconMenuBottomBar(
                    icon = R.drawable.mi_search,
                    contentDescription = "Pencarian",
                    onClick = {
                        // FITUR PENCARIAN
                    }
                )
            }
        }

    }
}

@Composable
fun BannerCard(
    images: List<Int>,
    currentPage: Int,
    coroutineScope: CoroutineScope,
    context: Context,
    onPageChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(images[currentPage]),
                contentDescription = "Banner Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalPagerIndicator(
                count = images.size,
                activeIndex = currentPage,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 16.dp),
                activeColor = Color.White,
                inactiveColor = Color.Gray
            )
        }
    }

    // Auto-scroll to the next slide every 3 seconds

    LaunchedEffect(Unit) {

        while (true) {
            delay(3000)
            coroutineScope.launch {
                onPageChange((currentPage + 1) % images.size)
            }
        }
    }
}

@Composable
fun HorizontalPagerIndicator(
    count: Int,
    activeIndex: Int,
    activeColor: Color = Color.White,
    inactiveColor: Color = Color.Gray,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 8.dp,
    spacing: Dp = 4.dp
) {
    Row(modifier = modifier) {
        for (i in 0 until count) {
            val color = if (i == activeIndex) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .size(indicatorSize)
                    .background(color = color)
            )
            if (i < count - 1) {
                Spacer(modifier = Modifier.width(spacing))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureMenuCard(apiService: ApiService, filteredTipsList: List<TipData>) {
    val context = LocalContext.current



    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Feature and Menu Card",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconMenuFitur(
                    icon = R.drawable.icon_fitur_pemindai_buah,
                    contentDescription = "Pemindai Buah",
                    text = "Pemindai Buah",
                    onClick = {
                        val intent = Intent(context, PemindaiBuahActivity::class.java)
                        context.startActivity(intent)
                    }
                )
                IconMenuFitur(
                    icon = R.drawable.icon_fitur_pencarian_resep,
                    contentDescription = "Pencarian Resep",
                    text = "Pencarian Resep",
                    onClick = {
                        val intent = Intent(context, PencarianResepActivity::class.java)
                        context.startActivity(intent)
                    }
                )
                IconMenuFitur(
                    icon = R.drawable.icon_fitur_artikel_buah,
                    contentDescription = "Artikel Buah",
                    text = "Artikel Buah",
                    onClick = {
                        val intent = Intent(context, WelcomeActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tips menarik hari ini",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(filteredTipsList) { tip ->
                    TipItem(
                        Gambar = tip.Gambar,
                        title = tip.title,
                        Tag = tip.Tag,
                        link = tip.link
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun IconMenuFitur(
    icon: Int,
    contentDescription: String,
    text: String? = null,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.White, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = onClick) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = contentDescription,
                    modifier = Modifier.size(30.dp)
                        .fillMaxWidth()
                )
            }
        }
        if (text != null) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun IconMenuBottomBar(
    icon: Int,
    contentDescription: String,
    text: String? = null,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = onClick) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = contentDescription,
                    modifier = Modifier.size(30.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

data class TipData(
    val id: Int,
    val title: String,
    val link: String,
    val Gambar: String,
    val Tag: String
)

@ExperimentalMaterial3Api
@Composable
fun TipItem(Gambar: String, title: String, Tag: String, link: String) {
    val context = LocalContext.current

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        modifier = Modifier.padding(vertical = 4.dp),
        onClick = {
            // Tindakan untuk mengarahkan ke link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            context.startActivity(intent)
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(Gambar),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .height(200.dp), // Tambahkan tinggi untuk gambar
                contentScale = ContentScale.Crop // Menggunakan ContentScale.Crop untuk memastikan gambar terpotong dengan benar
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = Tag,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
        HomeScreen()
}
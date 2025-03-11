package com.example.bitcampusnavigator

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bitcampusnavigator.ui.theme.BITCampusNavigatorTheme

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BITCampusNavigatorTheme {
                AboutScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About App",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { (context as? ComponentActivity)?.finish() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // App Logo
            Image(
                painter = painterResource(id = R.drawable.logo112),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 20.dp),
                contentScale = ContentScale.Fit
            )

            // App Name
            Text(
                text = "BIT Campus Navigator",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Sections
            SectionWithLeftAlignedContent(
                title = "About the App",
                content = "Welcome to BIT Campus Navigator! This app helps students, faculty, and visitors navigate BIT campus easily."
            )

            SectionWithLeftAlignedContent(
                title = "Developed By",
                content = """
                    Polytechnic Final Year Students:
                    - Miss. Menka Dewangan
                    - Miss. Priti Bhute
                    - Miss. Swati Bambode
                    - Miss. Muskan Sayyad
                    - Miss. Ashwini Mule
                    
                    In Guidance of:
                    - Miss. Maheen Quazi (Project Guide)
                    - Miss. Aarti Vaidya (Head of the Department)
                """.trimIndent()
            )

            SectionWithLeftAlignedContent(
                title = "Features",
                content = """
                    ✅ Explore campus buildings and floors.
                    ✅ View detailed room information.
                    ✅ Interactive floor layouts with zoom functionality.
                    ✅ Search for specific rooms or buildings.
                    ✅ Important contacts for quick access.
                """.trimIndent()
            )

            SectionWithLeftAlignedContent(
                title = "Technology Used",
                content = """
                    - Built with Android Jetpack Compose.
                    - Uses Google Maps for navigation.
                    - Modern Material Design 3 components.
                    - Kotlin for clean and efficient code.
                """.trimIndent()
            )

            SectionWithLeftAlignedContent(
                title = "Android Specifications",
                content = """
                    - Minimum SDK Version: 24 (Android 7.0 Nougat).
                    - Target SDK Version: 34 (Android 14).
                    - Optimized for modern Android devices.
                """.trimIndent()
            )
// 🔹 Contact Us
            SectionWithLeftAlignedContent(
                title = "Contact Us",
                content = """
        📧 Email: Swatibambode@gmail.com
        🌐 Website: https://www.bitedu.co.in/
        📍 Address: R9HP+W7X, Bamni, Ballarpur, Maharashtra 442901
    """.trimIndent()
            )

// 🔹 Campus Website (Centered)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "BIT Official Website",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                ClickableText(
                    text = AnnotatedString("🌍 Visit BIT Official Website"),
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = {
                        val uri = Uri.parse("https://www.bitedu.co.in/")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(intent)
                    }
                )
            }

// 🔹 Future Updates
            SectionWithLeftAlignedContent(
                title = "Future Updates",
                content = """
        🚀 Offline maps support.
        📲 Push notifications for campus events.
        🔗 Integration with Google Calendar for class schedules.
        🎉 More customization options for a personalized experience.
    """.trimIndent()
            )

// 🔹 Acknowledgments (Centered)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Acknowledgements",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = " Special thanks to the faculty members, testers, and  users who helped improve this app. Your support means everything!",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }


            // Enhanced Message Section
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "✨ A Message for You ✨",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Thank you for using BIT Campus Navigator! We hope this app makes your campus experience smoother and more enjoyable. Explore, discover, and make the most of your time at BIT! 🚀",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }

            // Version Information
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Version: 1.0.0",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun SectionWithLeftAlignedContent(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 10.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center, // Keep title centered
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = content,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            textAlign = TextAlign.Start // Align content to the left
        )
    }
}


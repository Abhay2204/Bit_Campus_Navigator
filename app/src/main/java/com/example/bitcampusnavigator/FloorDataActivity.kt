package com.example.bitcampusnavigator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bitcampusnavigator.ui.theme.BITCampusNavigatorTheme

class FloorDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BITCampusNavigatorTheme {
                val buildingId = intent.getIntExtra("buildingId", -1)
                val floorNumber = intent.getIntExtra("floorNumber", -1)
                val building = buildings.find { it.id == buildingId }
                val floor = building?.floors?.find { it.floorNumber == floorNumber }

                if (building != null && floor != null) {
                    FloorDataScreen(building, floorNumber)
                } else {
                    ErrorScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloorDataScreen(building: Building, floorNumber: Int) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    val floor = building.floors.find { it.floorNumber == floorNumber }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${building.name} - Floor $floorNumber",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { (context as? ComponentActivity)?.finish() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White // Ensures back icon is visible
                        )
                    }

                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            Icons.Filled.Menu, // Updated Menu Icon
                            contentDescription = "Menu",
                            tint = Color.White, // Change icon color
                            modifier = Modifier.size(28.dp) // Adjust icon size
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("About App") },
                            onClick = { val intent = Intent(context, AboutActivity::class.java)
                                context.startActivity(intent) }
                        )
                        DropdownMenuItem(
                            text = { Text("Search Rooms") },
                            onClick = { val intent = Intent(context, SearchActivity::class.java)
                                context.startActivity(intent) }
                        )
                        DropdownMenuItem(
                            text = { Text("Important Contacts") },
                            onClick = {  val intent = Intent(context, ImportantContactsActivity::class.java)
                                context.startActivity(intent) }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (floor != null) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                ) {
                    items(floor.rooms) { room ->
                        RoomCard(room)
                    }
                }

                Button(
                    onClick = {
                        val intent = Intent(context, FloorLayoutActivity::class.java).apply {
                            putExtra("buildingId", building.id)
                            putExtra("floorNumber", floorNumber)
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "View Floor Layout")
                }
            }
        }
    }
}

@Composable
fun RoomCard(room: Room) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Room Number: ${room.id}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Name: ${room.name}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium

            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display Image If Available
            room.image?.let { imageResId ->
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Room Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            } ?: Text( // If no image, show a placeholder text
                text = "No Image Available",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Description: ${room.description}",
                fontSize = 14.sp
            )
            Text(
                text = "Position: ${room.position}",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ✅ Use HorizontalDivider instead of Divider
            HorizontalDivider(
                color = Color.Gray.copy(alpha = 0.5f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Error: Data Not Found",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}

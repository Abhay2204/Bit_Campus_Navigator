package com.example.bitcampusnavigator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bitcampusnavigator.ui.theme.BITCampusNavigatorTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BITCampusNavigatorTheme {
                CampusMapScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusMapScreen() {
    // Get the current context
    val context = LocalContext.current

    // Define the four coordinates for the edges
    val coordinates = listOf(
        LatLng(19.83195752078194, 79.3858618936574),
        LatLng(19.82977752005949, 79.38487484077864),
        LatLng(19.829360521929445, 79.38684323940748),
        LatLng(19.831841351985553, 79.3872118965713)
    )

    // Create a LatLngBounds to cover the area
    val bounds = LatLngBounds.Builder().apply {
        coordinates.forEach { include(it) }
    }.build()

    // Define multiple pin locations with standard colors
    val pins = listOf(
        Pin(
            title = " Administrative Building",
            description = "The main administrative building of BIT.",
            location = LatLng(19.8299800867168, 79.38600403254246),
            color = BitmapDescriptorFactory.HUE_VIOLET // #6200EE
        ),
        Pin(
            title = "Electrical || Electronics & Telecom. Building",
            description = "Home to the Electrical and Electronics & Telecom. departments.",
            location = LatLng(19.830290225151582, 79.38605762799038),
            color = BitmapDescriptorFactory.HUE_BLUE // #3700B3
        ),
        Pin(
            title = "Civil& Mechanical Department",
            description = "Home to the Civil department.",
            location = LatLng(19.83092703280895, 79.38624805931526),
            color = BitmapDescriptorFactory.HUE_CYAN // #03DAC5
        ),
        Pin(
            title = "M.C.A || M.B.A || Computer Science Department",
            description = "Home to the CSE Engineering department.",
            location = LatLng(19.83060552776009, 79.38616497459064),
            color = BitmapDescriptorFactory.HUE_RED // #B00020
        ),
        Pin(
            title = "Mining and Food Department",
            description = "Home to the Mining and Food Engineering department.",
            location = LatLng(19.8313471300779, 79.38632925587349),
            color = BitmapDescriptorFactory.HUE_ROSE // #FF0266
        ),
        Pin(
            title = "Boys Hostel",
            description = "The boys' hostel for BIT students.",
            location = LatLng(19.831721609787138, 79.38645668936044),
            color = BitmapDescriptorFactory.HUE_MAGENTA // #7E3FF0
        ),
        Pin(
            title = "ITI",
            description = "Home to the ITI department.",
            location = LatLng(19.831188523204403, 79.38684096650884),
            color = BitmapDescriptorFactory.HUE_GREEN // #6A1B9A
        ),
        Pin(
            title = "Canteen",
            description = "The central canteen for students and staff.",
            location = LatLng(19.830913766615403, 79.38677079200575),
            color = BitmapDescriptorFactory.HUE_ORANGE // #6A1B9A
        ),
        Pin(
            title = "Workshop",
            description = "The central workshop for practical training.",
            location = LatLng(19.83059619022571, 79.38668923785349),
            color = BitmapDescriptorFactory.HUE_YELLOW // #FFFFFF (Yellow as fallback)
        )

    )

    // Set the camera position to focus on the bounds with a higher zoom level
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(pins[0].location, 18f) // Focus on the first pin
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "BIT Campus Navigator",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the Google Map inside a Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(
                        mapType = MapType.SATELLITE, // Set to satellite view
                        latLngBoundsForCameraTarget = bounds // Restrict the map to the defined area
                    ),
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = true // Enable zoom controls for better user experience
                    )
                ) {
                    // Add a marker for each pin
                    pins.forEach { pin ->
                        Marker(
                            state = MarkerState(position = pin.location),
                            title = pin.title,
                            snippet = pin.description,
                            icon = BitmapDescriptorFactory.defaultMarker(pin.color) // Set custom pin color
                        )
                    }
                }
            }

            // Display the address inside a Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "R9HP+W7X, Bamni, Ballarpur, Maharashtra 442901",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            // Enter BIT Campus Button
            Button(
                onClick = {
                    // Navigate to the BuildingListActivity
                    val intent = Intent(context, BuildingListActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Enter BIT Campus",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

data class Pin(
    val title: String,
    val description: String,
    val location: LatLng,
    val color: Float // Hue value for the pin color (0 to 360)
)
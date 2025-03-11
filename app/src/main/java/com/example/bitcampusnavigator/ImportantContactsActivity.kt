package com.example.bitcampusnavigator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bitcampusnavigator.ui.theme.BITCampusNavigatorTheme


class ImportantContactsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BITCampusNavigatorTheme {
                ImportantContactsScreen()
            }
        }
    }
}

// Data class for Contact
data class Contact(
    val name: String,
    val number: String,
    val designation: String,
    val callTimings: String,
    val description: String
)

// Sample list of important contacts
val importantContacts = listOf(
    Contact(
        name = "Dr.Rajni Kant",
        number = "9834602629",
        designation = "Principal",
        callTimings = "11:00 AM - 4:00 PM",
        description = "Please contact only for academic or departmental issues."
    ),
    Contact(
        name = "Mr. Sanjay Wasade",
        number = "+91 9823047225",
        designation = "Working President",
        callTimings = "10:00 AM - 4:00 PM",
        description = "For administrative queries only."
    ),
    Contact(
        name = "Dr. Archana G. Nimkar ",
        number = "+91 8668711505",
        designation = "Assistant Professor, Science & Humanities",
        callTimings = "10:00 AM - 4:00 PM",
        description = " Women Development cell ,Grievance Redressal Committee,"
    ),
    Contact(
        name = "Dr. Amruta Ballal",
        number = "+91 9762002777",
        designation = "Head ,training & Placement",
        callTimings = "11:00 AM - 4:00 PM",
        description = " Student training & Placement Cell  "
    ),
    Contact(
        name = "Miss. Jyoti Morai ",
        number = "+91 8668234476",
        designation = "Training & Placement",
        callTimings = "11:00 AM - 4:00 PM",
        description = "Student training & Placement Cell  "
    ),
    Contact(
        name = "Miss. Aarti Vaidya",
        number = "+91 9850416542",
        designation = "HOD(CO,Diploma)",
        callTimings = "11:00 AM - 4:00 PM",
        description = "HOD(CO,Diploma) "
    ),
    Contact(
        name = "Police Inspector, Ballarpur ",
        number = " 07172-240327",
        designation = "Police Inspector , Ballarpur ",
        callTimings = "11:00 AM - 4:00 PM",
        description = "police Station , Ballarpur "
    ),
    Contact(
        name = "Prof. Hirendra R. Hajare ",
        number = " 8087174210",
        designation = "HOD, (CO, Btech) ",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD, (CSE, Btech) "
    ),
    Contact(
        name = "Prof. Sudhir Pode ",
        number = " 9823166518",
        designation = "HOD ,MBA ",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD,MBA "
    ),
    Contact(
        name = "Prof. Prashant Walke ",
        number = " 8421818298",
        designation = "HOD ,ME ",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD,ME "
    ),
    Contact(
        name = "Prof. Neeraj Bais ",
        number = " 8624017254",
        designation = "HOD ,Civil ",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD,Civil "
    ),
    Contact(
        name = "Prof. Vishal Pimpalkar",
        number = " 8624017254",
        designation = "HOD ,EE ",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD,EE "
    ),
    Contact(
        name = "Prof. Manoj Giradkar",
        number = " 8007848311",
        designation = "HOD ,Mining ",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD, Mining "
    ),
    Contact(
        name = "Prof. Sumedh Manwatkar",
        number = "97640099371",
        designation = "HOD ,MCA",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD, MCA "
    ),
    Contact(
        name = "Prof. Deepak Mhaske",
        number = "8805997885",
        designation = "HOD ,ETE",
        callTimings = "11:00 AM - 4:00 PM",
        description = ", HOD, Ete "
    )

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportantContactsScreen() {
    LocalContext.current
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Important Contacts",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        ),
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
                    .shadow(8.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "📢 Please reach out only for urgent or essential matters , Thank you!",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search by Name or Designation") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .shadow(6.dp, RoundedCornerShape(12.dp))
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
            )

            val filteredContacts = importantContacts.filter {
                it.name.contains(searchQuery.text, ignoreCase = true) ||
                        it.designation.contains(searchQuery.text, ignoreCase = true)
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredContacts) { contact ->
                    ContactCard(contact = contact)
                }
            }
        }
    }
}


@Composable
fun ContactCard(contact: Contact) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .shadow(6.dp, RoundedCornerShape(16.dp)), // 🔷 Soft Elevation
        shape = RoundedCornerShape(16.dp), // 🟢 Rounded Corners
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) // 🟢 Increased Padding
        ) {
            // 🏷 Name & Designation
            Text(
                text = contact.name,
                fontSize = 20.sp, // 🔷 Larger Font
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp)) // 🔷 More Spacing
            Text(
                text = contact.designation,
                fontSize = 16.sp, // 🔷 Increased Font Size
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(10.dp)) // 🔷 Extra Spacing

            // 📞 Phone Number (Without Icon)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Phone: ${contact.number}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )

                IconButton(
                    onClick = {
                        clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(contact.number))
                        Toast.makeText(context, "Phone number copied!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .size(32.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = "Copy",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))



            Spacer(modifier = Modifier.height(8.dp))

            // ⏰ Call Timings (Without Icon)
            Text(
                text = "Call Timings: ${contact.callTimings}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 📝 Description
            Text(
                text = "Note: ${contact.description}",
                fontSize = 14.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

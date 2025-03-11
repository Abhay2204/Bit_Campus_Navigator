package com.example.bitcampusnavigator

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.google.android.gms.maps.model.LatLng

class BuildingListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BITCampusNavigatorTheme {
                BuildingListScreen(buildings = buildings)
            }
        }
    }
}

// Data class for Building
data class Building(
    val id: Int,
    val name: String,
    val location: LatLng,
    val image: Int, // Drawable resource ID for the building image
    val description: String,
    val floors: List<Floor> // List of floors for the building
)

// Data class for Floor
data class Floor(
    val floorNumber: Int,
    val description: String, // Description of the floor
    val rooms: List<Room>, // List of rooms on the floor
    val layoutImage: Int? // Drawable resource ID for the floor layout image (optional)

)

// Data class for Room
data class Room(
    val id: String,
    val name: String,
    val description: String,
    val position: String, // Left, Right, or Center
    val image: Int? // Drawable resource ID for the room image (optional)
)

// Sample list of buildings with unique floor details
val buildings = listOf(
    Building(
        id = 1,
        name = "Administrative Building",
        location = LatLng(19.830034917940146, 79.38607803116696),
        image = R.drawable.administrative_building,
        description = "The main administrative building of BIT.",
        floors = listOf(
            Floor(
                floorNumber = 1,
                description = "Ground Floor: Administrative Offices and Principal's Office",
                rooms = listOf(
                    Room(id = "A-101", name = "Scholarship & EBC section", description = "Scholar ship and EBC form filling and inquiry  ", position = "Right", image = null),
                    Room(id = "A-102", name = "Exam  and Account section ", description = "Exam inquiry or mark sheet distribution and account section ", position = "Right", image = null),
                    Room(id = "A-103", name = "Office Room ", description = "Document storing room ", position = "Right", image = null),
                    Room(id = "A-106", name = "Kalpana Chawla Room ", description = "kalpana Chawla Classroom", position = "Right", image = null),
                    Room(id = "A-2", name = "Vipin Sir's Office ", description = "Admission Related Inquiry  ", position = "Right", image = R.drawable.vipinsir),
                    Room(id = "A-108", name = "Girl's Washroom", description = "Washroom for Girls", position = "Right", image = null),
                    Room(id = "A-23", name = "Document Office", description = "Library", position = "Left", image = null),
                    Room(id = "A-109", name = "Dr. Zafar Khan (Dean R & D )Office", description = "Dean's Office", position = "Left", image = null),
                    Room(id = "A-110", name = "Principal Sir's Office", description = "Principal Sir's Office", position = "Left", image = null),
                    Room(id = "A-109", name = "Chairman Sir's Office", description = "Chairman Sir's Office", position = "Left", image = null),

                ),
                layoutImage = R.drawable.ab_floor1
            ),
            Floor(
                floorNumber = 2,
                description = "First Floor: 1st Yr. classrooms , Computer lab,  stationary and Placement cell",
                rooms = listOf(
                    Room(id = "A-201", name = "Computer Center Lab-1", description = "Computer Lab for workshops .", position = "left", image = null),
                    Room(id = "A-202", name = "Stationary ", description = "Snacks, printing , stationary", position = "centre", image = R.drawable.store),
                    Room(id = "A-203", name = "EE, ETC Classroom", description = "Shriniwas Ramanujan Room & EE, ETC Classroom  ", position = "left", image = null),
                    Room(id = "A-204", name = "Career Guidance & Counselling Cell", description = "Career Guidance & Counselling Cell ", position = "centre", image = null),
                    Room(id = "A-205", name = "Placement Cell Office", description = "Placement  inquiry  and opportunities ", position = "centre", image = null),
                    Room(id = "A-206", name = "Dr. Vikram Sarabahi Incubation Centre", description = "Staff room And Office ", position = "right", image = null),
                    Room(id = "A-207", name = "A.P.J Kalam room", description = "A.P.J Kalam room ", position = "right", image = null),
                    Room(id = "A-208", name = "1st Yr. Classroom", description = "1st Yr. Classroom ", position = "right", image = null),
                    Room(id = "A-209", name = "First Aid Room", description = "First Aid  Room ", position = "right", image = null),
                ),
                layoutImage = R.drawable.ab_floor2
            ),
            Floor(
                floorNumber = 3,
                description = "Second Floor :library & Reading Area and rooms",
                rooms = listOf(
                    Room(id = "A-301", name = "Book Issue & Return Centre", description = "Book Issue & Return Centre", position = "right", image = R.drawable.liberary),
                    Room(id = "A-302", name = "Central Library", description = "Central Library", position = "Right", image = R.drawable.liberary),
                    Room(id = "A-303", name = "Reading Area", description = "Reading Area", position = "left", image = null),
                    Room(id = "A-304", name = "Reading Room", description = "Library", position = "left", image = null),
                    Room(id = "A-305", name = "Girl's Washroom", description = "Girl's Washroom", position = "left", image = null),
                    Room(id = "A-306", name = "Empty Room", description = "Empty Room", position = "Right", image = null),

                ),
                layoutImage = R.drawable.ab_floor3
            )
        )
    ),
    Building(
        id = 2,
        name = "Electrical || Electronics & Telecom. Building",
        location = LatLng(19.830290225151582, 79.38605762799038),
        image = R.drawable.electrical_b,
        description = "Home to the Electrical and Mechanical Engineering departments.",
        floors = listOf(
            Floor(
                floorNumber = 1,
                description = "Ground Floor : Electrical Labs , HOD's room & Btech Classrooms. ",
                rooms = listOf(
                    Room(id = "B-101", name = "Electrical Cad lab", description = "Electrical lab.", position = "Left", image = null),
                    Room(id = "B-102", name = "Basic Electronics & Power Electronics Lab", description = "Electronics Lab.", position = "Left", image = null),
                    Room(id = "B-103", name = "Basic Electrical & Networking Lab", description = "Basic Electrical & Networking Lab.", position = "Central", image = null),
                    Room(id = "B-104", name = "Tutorial", description = "Tutorial.", position = "Central", image = null),
                    Room(id = "B-105", name = "Prof. Shivani Rasekar HOD Room", description = "HOD Room.", position = "Right", image = null),
                    Room(id = "B-106", name = "3rd Yr. Classroom", description = "3rd Yr. Classroom.", position = "Right", image = null),
                    Room(id = "B-107", name = "2nd yr. Classroom", description = "2nd yr. Classroom.", position = "Right", image = null),
                    Room(id = "B-108", name = "Electrical Measurement & Instruction Lab", description = "Electrical Measurement & Instruction Lab.", position = "Central", image = null),
                    Room(id = "B-109", name = "Staff Washroom", description = "Staff Washroom.", position = "Left", image = null),
                    Room(id = "B-110", name = "Boy's Washroom", description = "Boy's Washroom.", position = "Left", image = null),

                    ),
                layoutImage = R.drawable.ele_floor1
            ),
            Floor(
                floorNumber = 2,
                description = "First Floor: Computer Lab , Staff rooms and Classrooms.",
                rooms = listOf(
                    Room(id = "B-201", name = "Computer Centre", description = "Computer Centre.", position = "Left", image = R.drawable.ele_ing1),
                    Room(id = "B-202", name = "Electrical Dept Staff Room", description = "Electrical Dept Staff Room.", position = "Right", image = null),
                    Room(id = "B-203", name = "Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "B-204", name = "Computer Centre", description = "Computer Centre.", position = "Right", image = null),
                    Room(id = "B-205", name = "Digital Electronics & Microprocessor Lab", description = "Digital Electronics & Microprocessor Lab.", position = "Centre", image = null),
                    Room(id = "B-206", name = "Switchgear Lab", description = "Switchgear Lab.", position = "Centre", image = null),
                    Room(id = "B-207", name = "Classroom", description = "Lecture hall.", position = "Left", image = null),
                    Room(id = "B-208", name = "Boy's Washroom", description = "Boy's Washroom.", position = "Left", image = null),

                    ),
                layoutImage = R.drawable.ele_floor2
            ),
            Floor(
                floorNumber = 3,
                description = "Second Floor: Girl's Common Room,  Library and Classrooms.",
                rooms = listOf(
                    Room(id = "B-301", name = "Girl's Common Room", description = "Girl's Common Room.", position = "Left", image = null),
                    Room(id = "B-302", name = "Classroom", description = "Lecture hall.", position = "Left", image = null),
                    Room(id = "B-303", name = "Departmental Library", description = "Departmental Library.", position = "Centre", image = null),
                    Room(id = "B-304", name = "Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "B-305", name = "Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "B-306", name = "Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "B-307", name = "Tutorial Room", description = "Tutorial Room.", position = "Centre", image = null),
                    Room(id = "B-308", name = "Girl's Washroom", description = "Washroom.", position = "Left", image = null),

                    ),
                layoutImage = R.drawable.ele_floor3
            )
        )
    ),
    Building(
        id = 3,
        name = "M.B.A || M.C.A || CSE Building",
        location = LatLng(19.830034917940146, 79.38607803116696),
        image = R.drawable.cse_build,
        description = "Home to the M.B.A || M.C.A || Computer Science and Engineering department.",
        floors = listOf(
            Floor(
                floorNumber = 1,
                description = "Ground Floor :M.B.A Classrooms, Smart Class & Seminar Hall",
                rooms = listOf(
                    Room(id = "C-101", name = "M.B.A Tutorial Room", description ="M.B.A Tutorial Room", position = "Left", image = null),
                    Room(id = "C-102", name = "HOD Room", description = "HOD Room", position = "left", image = null),
                    Room(id = "C-103", name = "Classroom", description = "Classroom", position = "Left", image = null),
                    Room(id = "C-105", name = "Exam Control Room", description = "Exam Control Room", position = "Right", image = null),
                    Room(id = "C-106", name = "Smart Class Classroom", description = "Smart Class Classroom", position = "Right", image = null),
                    Room(id = "C-107", name = "Seminar hall", description = "Seminar hall.", position = "Centre", image = null),
                    Room(id = "C-108", name = "Language Lab", description = "Language lab.", position = "Centre", image = null),
                    Room(id = "C-109", name = "Boy's Washroom", description = "Boy's Washroom", position = "Left", image = null),
                ),
                layoutImage = R.drawable.cs_floor1
            ),
            Floor(
                floorNumber = 2,
                description = "First Floor: Polytechnic classrooms , Poly CO HOD's Room & Programming Labs",
                rooms = listOf(
                    Room(id = "C-201", name = "Classroom", description = "Classroom", position = "Left", image = null),
                    Room(id = "C-202", name = "Mr. Balvir Chand HOD (Electronics & Telecoms.) room", description = " Electronics & Telecoms.'s  Head of the Department room", position = "Left", image = null),
                    Room(id = "C-203", name = "Departmental Library", description = "Departmental Library", position = "Left", image = null),
                    Room(id = "C-204", name = "CO Poly. 3rd Yr. Classroom", description = "Computer Science Polytechnic 3rd Yr. Classroom", position = "Left", image = null),
                    Room(id = "C-206", name = "Miss. Aarti Vaidya HOD Poly. Computer Department", description = "CO Polytecnics  Head of the Department office.", position = "Center", image = null),
                    Room(id = "C-207", name = "CO Poly. 2nd yr. Classroom", description = "CS Poly. 2nd yr. Classroom", position = "Right", image = null),
                    Room(id = "C-208", name = "Advance Programming lab-1", description = "Advance Programming lab-1", position = "Center", image = null),
                    Room(id = "C-209", name = "Faculty Room", description = "Office Room for Teachers and Faculty.", position = "Centre", image = null),
                    Room(id = "C-210", name = "Advance Programming lab-2", description = "Advance Programming lab-2", position = "Right", image = null),
                    Room(id = "C-211", name = "Girl's Washroom", description = "Girl's Washroom", position = "left", image = null),
                ),
                layoutImage = R.drawable.cs_floor2
            ),
            Floor(
                floorNumber = 3,
                description = "Second Floor: Btech classrooms , Btech CO HOD's Room and Computer labs",
                rooms = listOf(
                    Room(id = "C-301", name = "CS 4th Yr. Btech Classroom ", description = "4th Yr. Btech Classroom ", position = "Left", image = null),
                    Room(id = "C-302", name = "CS 3rd Yr. Btech Classroom ", description = "3rd Yr. Btech Classroom ", position = "Left", image = null),
                    Room(id = "C-303", name = "CS 2nd Yr. Classroom", description = "Faculty office.", position = "Center", image = null),
                    Room(id = "C-304", name = "Office", description = "Office", position = "Right", image = null),
                    Room(id = "C-305", name = "Prof. Hirendra Hajare HOD's Room", description = "CSE Btech HOD's Room", position = "Right", image = null),
                    Room(id = "C-306", name = "Programming Lab-1", description = "Programming Lab-1", position = "Right", image = null),
                    Room(id = "C-307", name = "Staff Room", description = "Faculty office.", position = "Right", image = null),
                    Room(id = "C-308", name = "Network Programming lab-2", description = "Network Programming lab-2.", position = "Right", image = null),
                    Room(id = "C-309", name = "Hardware & Maintenance Lab", description = "Hardware & Maintenance Lab", position = "Center", image = null),
                    Room(id = "C-310", name = "Tutorial Room", description = "Tutorial Room", position = "Centre", image = null),
                    Room(id = "C-311", name = "M.C.A HOD's Room", description = "M.C.A HOD's Room", position = "left", image = null),
                ),
                layoutImage = R.drawable.cs_floor3
            )
        )
    ),
    Building(
        id = 4,
        name = "Mechanical || Civil Building",
        location = LatLng(19.8295, 79.3865),
        image = R.drawable.mech_civil,
        description = "Home to the Mechanical and Civil Engineering departments.",
        floors = listOf(
            Floor(
                floorNumber = 1,
                description = "Ground Floor: Mechanical Engineering Labs , Drawing Hall  & Seminar Hall .",
                rooms = listOf(
                    Room(id = "D-101", name = "Mechanical Engg. Material & Measurement Lab", description = "Mechanical Engg. Material & Measurement Lab", position = "Left", image = null),
                    Room(id = "D-102", name = "Drawing Hall", description = "Drawing Hall.", position = "Left", image = null),
                    Room(id = "D-103", name = "GYM", description = "Gym", position = "Right", image = null),
                    Room(id = "D-104", name = "Refrigeration & Air Conditioning & Thermal Engg. Lab", description = "Refrigeration & Air Conditioning & Thermal Engg. Lab", position = "Right", image = null),
                    Room(id = "D-105", name = "Seminar Hall", description = "Seminar Hall.", position = "Centre", image = null),
                    Room(id = "D-106", name = "CNC Machine Shop", description = "CNC Machine Shop", position = "Centre", image = null),
                    Room(id = "D-107", name = "Boy's Washroom", description = "Boy's Washroom", position = "Left", image = null)


                ),
                layoutImage = R.drawable.civ_floor1
            ),
            Floor(
                floorNumber = 2,
                description = "First Floor: Faculty Offices , Tutorial room and Classrooms.",
                rooms = listOf(
                    Room(id = "D-201", name = "Classroom", description = "Classroom", position = "Left", image = null),
                    Room(id = "D-202", name = "Classroom ", description = "Classroom", position = "left", image = null),
                    Room(id = "D-203", name = "Language Lab", description = "Language Lab", position = "Left", image = null),
                    Room(id = "D-204", name = "Classroom", description = "Classroom", position = "Right", image = null),
                    Room(id = "D-205", name = "Classroom", description = "Classroom", position = "Right", image = null),
                    Room(id = "D-206", name = "Faculty Office", description = "Faculty office.", position = "Right", image = null),
                    Room(id = "D-207", name = "Classroom", description = "Classroom", position = "Center", image = null),
                    Room(id = "D-208", name = "Tutorial Room", description = "Tutorial Room", position = "Centre", image = null),
                    Room(id = "D-209", name = "Girl's Washroom", description = "Girl's Washroom", position = "Left", image = null)


                ),
                layoutImage = R.drawable.civ_floor2
            ),
            Floor(
                floorNumber = 3,
                description = "Second Floor :CAD Cam Solid Modeling lab, Hod's Office and Classrooms.",
                rooms = listOf(
                    Room(id = "D-301", name = "Faculty Office", description = "Faculty office.", position = "Left", image = null),
                    Room(id = "D-302", name = "2nd yr. Classroom", description = "2nd yr. Classroom", position = "Left", image = null),
                    Room(id = "D-303", name = "3rd Yr. Classroom", description = "3rd Yr. Classroom", position = "Left", image = null),
                    Room(id = "D-304", name = "Tutorial Room", description = "Tutorial Room", position = "Right", image = null),
                    Room(id = "D-305", name = "Departmental Library", description = "Departmental Library", position = "Right", image = null),
                    Room(id = "D-306", name = "Cad Cam Solid Modeling Lab", description = "Cad Cam Solid Modeling Lab", position = "Right", image = null),
                    Room(id = "D-307", name = "Project Room", description = "Project Room", position = "Center", image = null),
                    Room(id = "D-208", name = "Mr. H.N. Gandate HOD's Room", description = "Mechanical Department HOD's Room", position = "Center", image = null),
                    Room(id = "D-209", name = "Boy's Washroom ", description = "Boy's Washroom", position = "Left", image = null)

                    ),
                layoutImage = R.drawable.civ_floor3
            )
        )
    ),
    Building(
        id = 5,
        name = "Mining and Food Department",
        location = LatLng(19.8295, 79.3865),
        image = R.drawable.mining,
        description = "Home to the Mining and Food Engineering departments.",
        floors = listOf(
            Floor(
                floorNumber = 1,
                description = "Ground Floor: Exam  ,Account & Scholarship Office, POLY Principal's Office & Labs",
                rooms = listOf(
                    Room(id = "E-112", name = "Exam Office & Scholarship Office & Account Office ", description = "Office Section.", position = "Centre", image = R.drawable.min_img1),
                    Room(id = "E-101", name = "Engg. Mechanics Lab", description = "Engg. Mechanics Lab.", position = "Left", image = null),
                    Room(id = "E-102", name = "Physics Lab", description = "Physics Lab.", position = "Left", image = null),
                    Room(id = "E-103", name = "Principal Prof. Shrikant Goje. Poly Room", description = "Prof. Shrikant Goje.", position = "Left", image = R.drawable.min_hod),
                    Room(id = "E-104", name = "Chemistry Lab", description = "Chemistry Lab.", position = "Left", image = null),
                    Room(id = "E-105", name = "Food technology Classroom", description = "Food technology Classroom.", position = "Right", image = null),
                    Room(id = "E-106", name = "Food technology Lab", description = "Food workshop.", position = "Right", image = null),
                    Room(id = "E-107", name = "HOD Room", description = "HOD Room.", position = "Right", image = null),
                    Room(id = "E-108", name = "High Voltage Engg. Lab", description = "High Voltage Engg. Lab.", position = "Right", image = null),
                    Room(id = "E-109", name = "Network Analysis Lab", description = "Network Analysis Lab.", position = "Centre", image = null),
                    Room(id = "E-110", name = "Reading Room", description = "Reading Room.", position = "Centre", image = null),
                    Room(id = "E-111", name = "Food Technology Lab", description = "Food workshop.", position = "Centre", image = null),
                    Room(id = "E-113", name = "Advance Electrical Workshop lab", description = "Electrical Workshop .", position = "Centre", image = null),
                    Room(id = "E-114", name = "Boy's Washroom", description = "Washroom.", position = "left", image = null),


                    ),
                layoutImage = R.drawable.min_floor1
            ),
            Floor(
                floorNumber = 2,
                description = "First Floor: Hod's Room and Food Technology's Classrooms.",
                rooms = listOf(
                    Room(id = "E-201", name = "Exam Control Room & Classroom", description = "Exam Control Room & Classroom.", position = "Left", image = null),
                    Room(id = "E-202", name = "1st Yr. Classroom", description = "Lecture hall.", position = "Left", image = null),
                    Room(id = "E-203", name = "Hod Room", description = "Hod Room.", position = "Left", image = null),
                    Room(id = "E-204", name = "Classroom", description = "Lecture hall.", position = "Left", image = null),
                    Room(id = "E-205", name = "B-tech Food Technology Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "E-206", name = "Diploma Food Technology Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "E-207", name = "HOD's Room", description = "HOD's Room.", position = "Right", image = null),
                    Room(id = "E-208", name = "Ms Swagata Kawle HOD Room(FC)", description = "HOD Room.", position = "Right", image = null),
                    Room(id = "E-209", name = "Diploma Food Technology Classroom", description = "Lecture hall.", position = "Center", image = null),
                    Room(id = "E-210", name = "Classroom", description = "Lecture hall.", position = "Center", image = null),
                    Room(id = "E-211", name = "1st Yr. Classroom", description = "Lecture hall.", position = "Center", image = null),
                    Room(id = "E-212", name = "Mathematics Lab & Classroom", description = "Mathematics Lab & Classroom.", position = "Center", image = null),
                    Room(id = "E-213", name = "B-Tech Food technology Classroom", description = "Lecture hall.", position = "Center", image = null),
                    Room(id = "E-214", name = "Girl's Washroom", description = "Girl's Washroom.", position = "Left", image = null),
                ),
                layoutImage = R.drawable.min_floor2
            ),
            Floor(
                floorNumber = 3,
                description = "Second Floor: Labs , Sports , Boys Common Rooms and Classrooms.",
                rooms = listOf(
                    Room(id = "E-301", name = "Classroom", description = "Lecture hall.", position = "Left", image = null),
                    Room(id = "E-302", name = "Mine Climate Laboratory", description = "Mine Climate Laboratory.", position = "Left", image = null),
                    Room(id = "E-303", name = "Dr. Rajni Kant Mining HOD Room", description = "HOD Room.", position = "Left", image = null),
                    Room(id = "E-304", name = "Classroom", description = "Lecture hall.", position = "Left", image = null),
                    Room(id = "E-305", name = "Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "E-306", name = "Classroom", description = "Lecture hall.", position = "Right", image = null),
                    Room(id = "E-308", name = "Sports Dept Boys Common Room", description = "Sports Room.", position = "Right", image = null),
                    Room(id = "E-309", name = "Classroom", description = "Lecture hall.", position = "Centre", image = null),
                    Room(id = "E-310", name = "Lab", description = "Lab.", position = "Centre", image = null),
                    Room(id = "E-311", name = "Lab", description = "Lab.", position = "Centre", image = null),
                    Room(id = "E-312", name = "Lab", description = "Lab.", position = "Centre", image = null),
                    Room(id = "E-313", name = "lab", description = "Lab.", position = "Centre", image = null),
                    Room(id = "E-314", name = "Gent's Washroom", description = "Gent's Washroom.", position = "Left", image = null),
                    Room(id = "E-315", name = "Girl's Washroom", description = "Girl's Washroom.", position = "Right", image = null),

                    ),
                layoutImage = R.drawable.min_floor3
            )
        )
    ),
    Building(
        id = 6,
        name = "Boys Hostel",
        location = LatLng(19.8305, 79.3855),
        image = R.drawable.hostel,
        description = "The boys' hostel for BIT students.",
        floors = listOf(
            Floor(
                floorNumber = 1,
                description = "Ground Floor: Common Room and Warden Office.",
                rooms = listOf(
                    Room(id = "101", name = "Common Room", description = "Common room for students.", position = "Center", image = null),
                    Room(id = "102", name = "Warden Office", description = "Warden's office.", position = "Left", image = null)
                ),
                layoutImage = null
            ),
            Floor(
                floorNumber = 2,
                description = "First Floor: Student Rooms.",
                rooms = listOf(
                    Room(id = "201", name = "Room 1", description = "Student room.", position = "Left", image = null),
                    Room(id = "202", name = "Room 2", description = "Student room.", position = "Right", image = null)
                ),
                layoutImage = null
            ),
            Floor(
                floorNumber = 3,
                description = "Second  Floor: Student Rooms.",
                rooms = listOf(
                    Room(id = "201", name = "Room 1", description = "Student room.", position = "Left", image = null),
                    Room(id = "202", name = "Room 2", description = "Student room.", position = "Right", image = null)
                ),
                layoutImage = null
            )
        )
    ),
    Building(
        id = 7,
        name = "Canteen",
        location = LatLng(19.8305, 79.3855),
        image = R.drawable.canteen,
        description = "The central canteen for students and staff.",
        floors = listOf(
            Floor(
                floorNumber = 1,
                description = "Ground Floor: Canteen Area and Kitchen.",
                rooms = listOf(
                    Room(id = "101", name = "Canteen", description = "Canteen area for students and Staff.", position = "Center", image = R.drawable.canteen),

                ),
                layoutImage = null
            )
        )
    ),

)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildingListScreen(buildings: List<Building>) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    // Use a LazyListState for smoother scrolling.
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "BIT Campus Buildings",
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
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("About App") },
                            onClick = {
                                val intent = Intent(context, AboutActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Search Rooms") },
                            onClick = {
                                val intent = Intent(context, SearchActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Important Contacts") },
                            onClick = {
                                val intent = Intent(context, ImportantContactsActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = buildings,
                key = { building -> building.id }
            ) { building ->
                BuildingCard(building = building, onClick = {
                    val intent = Intent(context, FloorActivity::class.java)
                    intent.putExtra("buildingId", building.id)
                    context.startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun BuildingCard(building: Building, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = building.image),
                contentDescription = building.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = building.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = building.description,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 18.sp
            )
        }
    }
}

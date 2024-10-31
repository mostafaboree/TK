package com.example.tk.ui.presentation.connecter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tk.R

@Preview(showBackground = true)
@Composable
fun SuggestedStudyPartnersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background) // Use theme background
            .padding(10.dp)
    ) {
        // Top bar with title
        Spacer(modifier = Modifier.height(16.dp))

                Text("Connect", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))



        // Tabs for "Suggestions" and "Chat"
        TabRow(
            selectedTabIndex = 0, // Assuming "Suggestions" is selected
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Tab(selected = true, onClick = {}) {
                Text("Suggestions", fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 16.dp))
            }
            Tab(selected = false, onClick = {}) {
                Text("Chat",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 16.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))





        // Filter icon (animated interaction could be added here)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Title: Suggested Study Partners
            Text(
                text = "Suggested Study Partners:",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color =Color(0xFF21B6B6),

                modifier = Modifier.padding(horizontal = 16.dp)
            )
            IconButton(onClick = { /* Filter action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),

                    tint = MaterialTheme.colors.primary,

                    contentDescription = "Filter",
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // List of study partners
        LazyColumn {
            items(5) { // Replace with your actual list of items
                StudyPartnerCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StudyPartnerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp // Increased elevation for more depth
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Name and target level
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Placeholder for profile image
                CircleAvatarPlaceholder()

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Reem Sayed",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp, // Larger font size for the name
                            color = MaterialTheme.colors.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TargetingLevelChip(level = "B1")
                    }
                    Text(
                        "Last seen online: Yesterday",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp)) // Added more space

            // Languages
            FlowRow {
                LanguageChip("English")
                LanguageChip("Arabic")
                LanguageChip("French")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Additional info: location, gender, age, etc.
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconInfo(icon = Icons.Default.LocationOn, info = "Egypt")
                IconInfo(icon = Icons.Default.Person, info = "Female")
                IconInfo(icon = Icons.Default.Face, info = "26")
                IconInfo(icon = Icons.Default.DateRange, info = "21 June 2023")
            }
        }
    }
}

@Composable
fun CircleAvatarPlaceholder() {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(MaterialTheme.colors.primary, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "RS", // Initials
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TargetingLevelChip(level: String) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Targeting: $level",
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun LanguageChip(language: String) {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colors.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = language,
            color = MaterialTheme.colors.primary,
            fontSize = 12.sp
        )
    }
}

@Composable
fun IconInfo(icon: ImageVector, info: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(info, fontSize = 12.sp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f))
    }
}

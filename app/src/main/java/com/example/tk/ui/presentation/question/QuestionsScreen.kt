package com.example.tk.ui.presentation.question

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TextButton
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.tk.R


@Preview(showBackground = true)
@Composable
fun QuestionsScreen() {
    var selectedTab by remember { mutableStateOf(0) } // 0 = Writing, 1 = Oral

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Questions", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
            backgroundColor = Color.White,
            elevation = 4.dp,
            actions = {
                TextButton(onClick = { /* Handle view change */ }) {
                    Text(text = "View As: Categories", color = Color(0xFF008080)) } }
        )

        // Tabs for "Writing" and "Oral"
        TabRow(
            selectedTabIndex = selectedTab,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = Color(0xFF008080)
        ) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 },
                modifier = Modifier.padding(vertical = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier =
                    Modifier.fillMaxWidth()) {

                    Icon(painter = painterResource(id = R.drawable.edit), contentDescription = null,
                        tint = Color(0xFF008080), modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                Text("Writing", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(4.dp))

                }
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 },
                modifier = Modifier.padding(vertical = 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()) {
                    Icon(painter = painterResource(id = R.drawable.record), contentDescription = null,
                        tint = Color(0xFF008080), modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                Text("Oral", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(4.dp))

            }
            }}

        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.background(color = Color(0xFF008080).copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp)).padding(vertical = 4.dp, horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
            ) {
            Text("Filter", fontWeight = FontWeight.Bold, fontSize = 18.sp,
                color = Color(0xFF008080))
            Icon(painter = painterResource(id = R.drawable.filter), contentDescription = null,
                tint = Color(0xFF008080), modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))

        when (selectedTab) {
            0 -> WritingTabScreen()  // Writing tab content
            1 -> OralTabScreen()      // Oral tab content
        }
    }}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WritingTabScreen() {
    // Content for the Writing Tab
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categoryList) { category ->
            CategoryCard(
                title = category.title,
                questionsCount = category.questionsCount,
                progress = category.progress,
                icon = category.icon
            )
        }
    }
}

@Composable
fun OralTabScreen() {
    // Content for the Oral Tab
    LazyColumn {
        items(5) {
            EnhancedQuestionCard(
                title = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps...",
                category = "Events",
                task = "Task 2",
                answers = "11 answers",
                date = "13 May 2023"
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Composable for the Category Card (Writing Tab)
@Composable
fun CategoryCard(title: String, questionsCount: Int, progress: Int, icon: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { /* Handle click */ },
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$questionsCount sur ${questionsCount * 2} Questions",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.background(Color(0xFF008080).copy(alpha = 0.2f))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF008080)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Progress $progress%",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = progress / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = Color(0xFF008080),
                backgroundColor = Color(0xFFE0E0E0)
            )
        }
    }
}

// Composable for the Question Card (Oral Tab)
@Composable
fun EnhancedQuestionCard(
    title: String,
    category: String,
    task: String,
    answers: String,
    date: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { /* Handle click */ },
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row {
                    EnhancedCategoryChip(text = category)
                    Spacer(modifier = Modifier.width(8.dp))
                    EnhancedCategoryChip(text = task)
                }
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = Color.Gray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = answers, fontSize = 14.sp, color = Color.Gray)
                }
                Text(text = date, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun EnhancedCategoryChip(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFF008080).copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF008080),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// Sample data for the categories in Writing Tab
data class Category(val title: String, val questionsCount: Int, val progress: Int, val icon: Int)

val categoryList = listOf(
    Category("Voyage", 10, 100, R.drawable.pla),
    Category("Immigration", 5, 50,R.drawable.art),
    Category("Technologie", 5, 50, R.drawable.tecn),
    Category("Art et Culture", 5, 50, R.drawable.pla),
    Category("Environment", 5, 50,R.drawable.profile),
    Category("Travel", 5, 50,R.drawable.pla)
)
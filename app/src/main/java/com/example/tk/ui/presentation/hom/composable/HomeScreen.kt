package com.example.tk.ui.presentation.hom.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

fun shouldShowTutorial(context: Context): Boolean {
    val prefs: SharedPreferences = context.getSharedPreferences("tutorial_prefs", Context.MODE_PRIVATE)
    return prefs.getBoolean("show_tutorial", true)
}

fun dismissTutorial(context: Context) {
    val prefs: SharedPreferences = context.getSharedPreferences("tutorial_prefs", Context.MODE_PRIVATE)
    prefs.edit().putBoolean("show_tutorial", false).apply()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Home",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                },
                modifier = Modifier.background(Color.White),
                actions = {
                    IconButton(onClick = { /* Handle notification icon click */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            tint = Color(0xFFb21B6B6)
                        )
                    }
                }
            )
        },
    ) { innerePadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerePadding)
        ) {
            Title()
            Spacer(modifier = Modifier.height(16.dp))
            StudyPlanList(studyPlanItems = generateDummyStudyPlan())
        }
    }
}

@Composable
fun Title() {
    Row {
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Hi",
            fontSize = 20.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "User Name",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

            color = Color(0xFFb21B6B6))

    }
}

data class StudyPlanItemData(
    val unitNumber: Int,
    val title: String,
    val description: String,
    val isLocked: Boolean,
    val processe: Float
)

// Dummy data generator
fun generateDummyStudyPlan(): List<StudyPlanItemData> {
    return listOf(
        StudyPlanItemData(1, "Unite 1:", " What is examate", isLocked = false, processe = 60f),
        StudyPlanItemData(2, "Unite 2:", " What is TCF", isLocked = true, 0f),
        StudyPlanItemData(3, "unites 3:", "Writing Tasks", isLocked = true, 0f),
        StudyPlanItemData(4, "Unite 4:", "Oral Task", isLocked = true, 0f)
    )
}

@Composable
fun StudyPlanList(studyPlanItems: List<StudyPlanItemData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        contentPadding = PaddingValues(10.dp) // No spacing between items
    ) {
        item {
            Text(
                "Study Plan",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,


                )
        }

        items(studyPlanItems.size) { index ->
            StudyPlanItem(
                unitNumber = studyPlanItems[index].unitNumber,
                title = studyPlanItems[index].title,
                description = studyPlanItems[index].description,
                isLocked = studyPlanItems[index].isLocked,
                isLastItem = index == studyPlanItems.size - 1,
                processe = studyPlanItems[index].processe
            )
        }
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun StudyPlanItem(
    unitNumber: Int,
    title: String,
    description: String,
    isLocked: Boolean,
    isLastItem: Boolean,
    processe: Float
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    androidx.compose.foundation.Canvas(modifier = Modifier.size(70.dp)) {
                        drawArc(
                            color = Color.Gray,
                            startAngle = -90f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                    Canvas(modifier = Modifier.size(70.dp)) {
                        drawArc(
                            color = Color(0xFFb21B6B6),
                            startAngle = -90f,
                            sweepAngle = (processe.toFloat() / 100) * 360f,
                            useCenter = false,
                            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                    if (isLocked) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Locked",
                            tint = Color.White,
                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.BottomEnd)
                                .background(Color.Gray, CircleShape)
                                .size(30.dp)
                        )
                    }

                    //CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(60.dp), strokeWidth = 10.dp)

                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(if (isLocked) Color.Gray else Color.White)
                            .border(
                                2.dp,
                                if (isLocked) Color.Gray else Color(0xFFb21B6B6),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = unitNumber.toString(),
                            fontSize = 28.sp,
                            color = if (isLocked) Color.White else Color.Blue,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    if (isLocked) {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Locked",
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.BottomEnd)
                                .background(Color.Gray, CircleShape)
                                .padding(2.dp)
                        )
                    }
                }
                if (!isLastItem) {
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .height(40.dp)
                            .background(if (isLocked) Color.Gray else Color(0xFFb21B6B6).copy(alpha = processe / 100f))
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isLocked) Color.Gray else Color.Black
                )

                Text(
                    text = description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isLocked) Color.Gray else Color.Black
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Home()
}

package com.example.tk.ui.presentation.hom.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tk.R

@Composable
fun BottomNavigationTabs(
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit,
    isTutorialHighlight: Boolean = false
) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.height(80.dp)
    ) {
        Tab.values().forEach { tab ->
            val isSelected = selectedTab == tab
            val backgroundColor by animateColorAsState(
                targetValue = if (!isSelected&&isTutorialHighlight) Color.White else Color.Transparent
            )
            val iconSize by animateDpAsState(targetValue = if (isSelected) 28.dp else 24.dp)
            val textColor = if (isSelected) Color(0xFF21B6B6) else Color.Gray

            BottomNavigationItem(
                icon = {
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            painter = when (tab) {
                                Tab.Home -> painterResource(id = R.drawable.homsimpledoor)
                                Tab.Connect ->painterResource(id = R.drawable.chatlines)  // Replace with correct icon
                                Tab.Questions -> painterResource(id = R.drawable.helpcircle)// Replace with correct icon
                                Tab.Tools ->painterResource(id = R.drawable.tool) // Replace with correct icon
                                Tab.Profile ->painterResource(id = R.drawable.profile) // Replace with correct icon
                            },
                            contentDescription = tab.name,
                            tint = textColor,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                },
                selected = isSelected,
                onClick = { onTabSelected(tab) },
                label = {
                    androidx.compose.material3.Text(
                        tab.name,
                        color = textColor,
                        fontSize = 12.sp
                    )
                },
                modifier = Modifier.background(backgroundColor, RoundedCornerShape(16.dp))
            )
        }
    }
}


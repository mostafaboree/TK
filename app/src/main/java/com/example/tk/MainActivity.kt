package com.example.tk
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tk.ui.presentation.connecter.SuggestedStudyPartnersScreen
import com.example.tk.ui.presentation.hom.composable.BottomNavigationTabs
import com.example.tk.ui.presentation.hom.composable.Home
import com.example.tk.ui.presentation.hom.composable.Tab
import com.example.tk.ui.presentation.hom.composable.TooltipTutorialOverlay
import com.example.tk.ui.presentation.hom.composable.TutorialStep
import com.example.tk.ui.presentation.hom.composable.dismissTutorial
import com.example.tk.ui.presentation.hom.composable.shouldShowTutorial
import com.example.tk.ui.presentation.question.QuestionsScreen
import com.example.tk.ui.theme.TKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TKTheme {
                MainContent()
            }
        }
    }
}


@Preview
@Composable
fun MainContent() {
    val context = LocalContext.current
    var showTutorial by remember { mutableStateOf(shouldShowTutorial(context)) }
    var tutorialStep by remember { mutableStateOf(TutorialStep.HighlightBottomNavigation) }
    var selectedTab by remember { mutableStateOf(Tab.Home) }

    val onSkipTutorial = {
        dismissTutorial(context)
        showTutorial = false
    }

    LaunchedEffect(tutorialStep) {
        selectedTab = when (tutorialStep) {
            TutorialStep.HighlightBottomNavigation -> Tab.Home
            TutorialStep.HighlightGridItem -> Tab.Questions
            TutorialStep.HighlightInnerButton -> Tab.Tools
            TutorialStep.Completed -> Tab.Home
        }
    }

    val onNextStep = {
        tutorialStep = when (tutorialStep) {
            TutorialStep.HighlightBottomNavigation -> TutorialStep.HighlightGridItem
            TutorialStep.HighlightGridItem -> TutorialStep.HighlightInnerButton
            TutorialStep.HighlightInnerButton -> TutorialStep.Completed
            TutorialStep.Completed -> TutorialStep.Completed
        }
        if (tutorialStep == TutorialStep.Completed) {
            onSkipTutorial()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationTabs(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                isTutorialHighlight = (tutorialStep == TutorialStep.HighlightBottomNavigation)
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                Tab.Home -> Home()
                Tab.Connect -> SuggestedStudyPartnersScreen()
                Tab.Questions -> QuestionsScreen()
                Tab.Tools -> ToolsScreen()
                Tab.Profile -> ProfileScreen()
            }
        }
    }

    if (showTutorial) {
        TooltipTutorialOverlay(
            currentStep = tutorialStep,
            onSkip = onSkipTutorial,
            onNext = onNextStep
        )
    }
}





@Composable
fun ToolsScreen() {
    Text("Tools Screen")
}

@Composable
fun ProfileScreen() {
    Text("Profile Screen")
}

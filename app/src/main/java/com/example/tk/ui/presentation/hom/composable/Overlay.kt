package com.example.tk.ui.presentation.hom.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tk.R

@Composable
fun TooltipTutorialOverlaya(
    currentStep: TutorialStep,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000))
    ) {
        when (currentStep) {
            TutorialStep.HighlightBottomNavigation -> {
                BalloonTooltip(
                    text = "This is the bottom navigation bar. Use it to switch between sections.",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 80.dp)
                )
                BoxToolTip(
                    Modifier
                        .align(Alignment.BottomStart)
                        .size(80.dp), R.drawable.tabhome)
            }
            TutorialStep.HighlightGridItem -> {
                BalloonTooltip(
                    text = "This is an important item in the Questions tab.",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 80.dp)
                )
                Image(painter = painterResource(id = R.drawable.tabconnector), contentDescription = "",
                    modifier = Modifier.align(Alignment.BottomStart).padding(start = 60.dp).size(80.dp))
            }
            TutorialStep.HighlightInnerButton -> {
                BalloonTooltip(
                    text = "This is an inner button in the Tools tab.",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 200.dp)
                )
                BoxToolTip(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .size(80.dp), R.drawable.tabques)
            }
            else -> {}
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onSkip) {
                Text("Skip Tutorial")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onNext) {
                Text("Next")
            }
        }
    }
}


//@Preview
@Composable
fun BalloonTooltip(text: String, modifier: Modifier = Modifier) {



}
@Composable
fun TooltipTutorialOverlay(
    currentStep: TutorialStep,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000))
    ) {
        when (currentStep) {
            TutorialStep.HighlightBottomNavigation -> {
                TooltipHighlight(
                    text = "Vous trouverez ici votre plan d'étude",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 80.dp)
                )
                BoxToolTip(
                    Modifier
                        .align(Alignment.BottomStart)
                        .size(80.dp),R.drawable.tabhome)
            }
            TutorialStep.HighlightGridItem -> {
                TooltipHighlight(
                    text = "Vous trouverez ici des partenaires d'étude" +
                            " et des personnes avec qui vous connecter",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 80.dp)
                )
                //BoxToolTip(Modifier.align(Alignment.BottomStart).size(180.dp).padding(start = 60.dp),R.drawable.tabconnector)
                Image(painter = painterResource(id = R.drawable.tabconnector), contentDescription ="" ,
                    modifier = Modifier.align(Alignment.BottomStart).padding(start = 60.dp).size(80.dp))
            }
            TutorialStep.HighlightInnerButton -> {
                TooltipHighlight(
                    text = "Voici les questions avec" +
                            " des réponses modèles",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(top = 200.dp)
                )
                BoxToolTip(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .size(80.dp), R.drawable.tabques)
            }
            else -> {}
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onSkip) {
                Text("Skip Tutorial")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onNext) {
                Text("Next")
            }
        }
    }
}

@Composable
fun TooltipHighlight(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Color(0xFF1F2937), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text,
            color = Color.White,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun BoxToolTip(modifier: Modifier,icon :Int ){
    Box(
        modifier= modifier
    ) {
        Image(painter = painterResource(id = icon), contentDescription = "",
            modifier = modifier )
    }
}

enum class Tab {
    Home, Connect, Questions, Tools, Profile
}

enum class TutorialStep {
    HighlightBottomNavigation,
    HighlightGridItem,
    HighlightInnerButton,
    Completed
}
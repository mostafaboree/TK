package com.example.tk.ui.presentation.question

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tk.R
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonHighlightAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.overlay.BalloonOverlayRoundRect
@Preview
@Composable
fun T(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Button(onClick = { /*TODO*/ }) {
            Text(text = "new chat what happened")
        }
        MyCanvas()


    }

}
@Composable
fun MyCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width/2
        val canvasHeight = size.height/8f

        // Background
        drawRect(
            color = Color(0xFF212121),
            size = size
        )

        // Text
        val text = "Vous trouverez ici des partenaires d'étude et des personnes avec qui vous connecter"


       // drawContext.drawTex(text, (canvasWidth - textWidth) / 2, (canvasHeight - textHeight) / 2, textPaint)

        // Triangle
        val trianglePath = Path().apply {
            moveTo(canvasWidth / 2, canvasHeight)
            lineTo(canvasWidth / 2 - 20, canvasHeight - 40)
            lineTo(canvasWidth / 2 + 20, canvasHeight - 40)
            close()
        }
        drawPath(trianglePath, Color.White)
    }
}
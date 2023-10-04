package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import tiv.edo.cyberobics.R


@Composable
fun Scene1(navigation: NavHostController) {

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val myFont = FontFamily(Font(R.font.chibola))

    val configuration = LocalConfiguration.current
    val screenHeight = (configuration.screenHeightDp * 2).toFloat()

    val animRadius = remember{
        Animatable(screenHeight)
    }

    val rotateElement = remember{
        Animatable(0f)
    }

    LaunchedEffect(Unit){
        delay(2800)
        navigation.navigate(Displays.DisplayTwo.scene)
    }

    LaunchedEffect(Unit){
        rotateElement.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1800, 0, LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        animRadius.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1300,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "The screen is prepearing...",
            color = Color.White,
            fontFamily = myFont,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 72.dp)
        )

        Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
            drawCircle(
                color = Color.White,
                radius = animRadius.value, // for example, to draw a circle with radius = 1/4 of the smallest dimension
                center = center // or any other position
            )
        })

        Image(
            painter = painterResource(id = R.drawable.el1),
            contentDescription = "element",
            modifier = Modifier
                .align(Alignment.Center)
                .rotate(rotateElement.value)
                .scale(2f)
        )
    }

    BackHandler(enabled = true) {
        //off
    }
}

@Composable
@Preview
fun PrevScene1(){
    val nav = rememberNavController()

    Scene1(navigation = nav)
}
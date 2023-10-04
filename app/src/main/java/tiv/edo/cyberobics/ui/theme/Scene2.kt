package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import tiv.edo.cyberobics.R

@Composable
fun Scene2(navigation: NavHostController) {

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val context = LocalContext.current as ComponentActivity


    val majorFont = FontFamily(Font(R.font.chibola))


    val animateYoffset = remember {
        Animatable(-400f)
    }

    LaunchedEffect(Unit) {
        animateYoffset.animateTo(
            targetValue = -128f,
            animationSpec = tween(
                durationMillis = 800,
                delayMillis = 300,
                easing = FastOutSlowInEasing
            )
        )
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = R.drawable.el2),
            contentDescription = "gnom",
            modifier = Modifier
                .align(Alignment.BottomStart)
        )

        Image(
            painter = painterResource(id = R.drawable.el3),
            contentDescription = "gnom",
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = animateYoffset.value.dp)
        ) {

            Box() {
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "playbutton",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Center)
                        .clickable {
                            navigation.navigate(Displays.DisplayThree.scene)
                        }
                )

                Text(
                    text = "Play",
                    fontFamily = majorFont,
                    color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


            Box {
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "play",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navigation.navigate(Displays.DisplayFour.scene)
                        }
                )

                Text(
                    text = "Settings",
                    fontFamily = majorFont,
                    color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


            Box {
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "play",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            context.finish()
                        }
                )

                Text(
                    text = "Exit",
                    fontFamily = majorFont,
                    color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    BackHandler(enabled = true) {
        //off
    }
}

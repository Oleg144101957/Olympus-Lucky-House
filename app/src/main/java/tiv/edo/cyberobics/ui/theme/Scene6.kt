package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
fun Scene6(navigation: NavHostController) {

    //Difficult level

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val myFont = FontFamily(Font(R.font.chibola))

    val context = LocalContext.current
    val sp = context.getSharedPreferences(Displays.PREFERENCES, Context.MODE_PRIVATE)
    val difficult = sp.getFloat(Displays.DIFFICULT, 1500f)

    val difficultLevel = remember {
        mutableStateOf(difficult)
    }



    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "Difficult Level",
            color = Color.White,
            fontFamily = myFont,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )

        Slider(
            value = difficultLevel.value,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .offset(y = 48.dp)
                .align(Alignment.Center),
            onValueChange = {
                difficultLevel.value = it
            },
            valueRange = 1500f..3000f
        )

        Text(
            text = "The current level is ",
            color = Color.White,
            fontSize = 32.sp,
            fontFamily = myFont,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 96.dp)
        )

        Text(
            text = "${difficultLevel.value.toInt()}",
            color = Color.White,
            fontFamily = myFont,
            fontSize = 48.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 150.dp)
        )

    }






}
package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tiv.edo.cyberobics.R

@Composable
fun Scene10(navigation: NavHostController) {

    //gnome size

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val context = LocalContext.current
    val sp = context.getSharedPreferences(Displays.PREFERENCES, Context.MODE_PRIVATE)
    val currentScale = sp.getFloat(Displays.SCALE, 1f)

    val scale = remember {
        mutableFloatStateOf(currentScale)
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
            painter = painterResource(id = R.drawable.el4),
            contentDescription = "gnom",
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale.value)
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "down",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(64.dp)
                .size(64.dp)
                .clickable {
                    val newScale = scale.value - 0.2f
                    if (newScale > 0.3f){
                        scale.value = newScale
                        sp.edit().putFloat(Displays.SCALE, newScale).apply()
                    }
                }
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "up",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(64.dp)
                .size(64.dp)
                .clickable {
                    val newScale = scale.value + 0.2f
                    if (newScale < 1.7f){
                        scale.value = newScale
                        sp.edit().putFloat(Displays.SCALE, newScale).apply()
                    }
                }
        )
    }
}


@Composable
@Preview
fun Prev10(){
    val nav = rememberNavController()
    
    Scene10(navigation = nav)
}
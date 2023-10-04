package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import tiv.edo.cyberobics.R

@Composable
fun Scene12(navigation: NavHostController) {
//rules

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        
        Text(
            text = "Catch the Gold: Dive into a magical world where a gnome rushes to collect falling gold. Rack up points with each treasure, but beware, bombs will reduce your score!",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}
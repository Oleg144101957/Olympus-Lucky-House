package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import tiv.edo.cyberobics.R

@Composable
fun Scene4(navigation: NavHostController) {

    val myFont = FontFamily(Font(R.font.chibola))

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    //Settings

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        
        Column(modifier = Modifier.align(Alignment.Center)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigation.navigate(Displays.DisplayFive.scene)
                        }
                )

                Text(
                    text = "name",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigation.navigate(Displays.DisplaySix.scene)
                        }
                )

                Text(
                    text = "Difficult",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigation.navigate(Displays.DisplaySeven.scene)
                        }
                )

                Text(
                    text = "Avatar",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigation.navigate(Displays.DisplayEight.scene)
                        }
                )

                Text(
                    text = "Gold Settings",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.textbg),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navigation.navigate(Displays.DisplayTwelve.scene)
                        }
                )

                Text(
                    text = "Help Screen",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}
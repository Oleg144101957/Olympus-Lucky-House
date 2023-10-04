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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tiv.edo.cyberobics.R

@Composable
fun Scene8(navigation: NavHostController) {
    
    //gold screen
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    
    val myFont = FontFamily(Font(R.font.chibola))

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        
        Column(modifier = Modifier
            .padding(bottom = 64.dp)
            .align(Alignment.BottomCenter)) {
            Box (modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.el5),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(4.dp)
                        .clickable {
                            //second name
                            navigation.navigate(Displays.DisplayNine.scene)
                        }
                )
                
                Text(
                    text = "Second name",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box (modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.el4),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(4.dp)
                        .clickable {
                            navigation.navigate(Displays.DisplayTen.scene)
                        }
                )

                Text(
                    text = "Gnome Size",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box (modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.el3),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(4.dp)
                        .clickable {
                            navigation.navigate(Displays.DisplayEleven.scene)
                        }

                )

                Text(
                    text = "Scores",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            Box (modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(id = R.drawable.el2),
                    contentDescription = "btn",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(4.dp)
                        .clickable {
                            navigation.navigate(Displays.DisplayTwelve.scene)
                        }

                )

                Text(
                    text = "Rules",
                    color = Color.White,
                    fontFamily = myFont,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview
fun Prev8(){
    val nav = rememberNavController()
    Scene8(navigation = nav)
}
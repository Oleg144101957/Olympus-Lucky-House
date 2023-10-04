package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tiv.edo.cyberobics.R

@Composable
fun Scene7(navigation: NavHostController) {
    //Avatar

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val context = LocalContext.current
    val sp = context.getSharedPreferences(Displays.PREFERENCES, Context.MODE_PRIVATE)


    val myFont = FontFamily(Font(R.font.chibola))

    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)

    val isBlackAndWhite1 = remember {
        mutableStateOf(true)
    }

    val isBlackAndWhite2 = remember {
        mutableStateOf(true)
    }

    val isBlackAndWhite3 = remember {
        mutableStateOf(true)
    }





    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        
        Column(modifier = Modifier.align(Alignment.Center)){
            Row (modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)){
                Image(
                    painter = painterResource(id = R.drawable.el8),
                    contentDescription = "1",
                    colorFilter = if (isBlackAndWhite1.value) ColorFilter.colorMatrix(matrix) else ColorFilter.colorMatrix(
                        ColorMatrix()
                    ),
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            isBlackAndWhite1.value = false
                            isBlackAndWhite2.value = true
                            isBlackAndWhite3.value = true
                            sp.edit().putInt(Displays.AVATAR, 1).apply()
                        }
                )

                Text(
                    text = "Hero 1",
                    color = Color.White,
                    fontFamily = myFont,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Row (modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)){
                Image(
                    painter = painterResource(id = R.drawable.el7),
                    contentDescription = "2",
                    colorFilter = if (isBlackAndWhite2.value) ColorFilter.colorMatrix(matrix) else ColorFilter.colorMatrix(
                        ColorMatrix()
                    ),
                    modifier = Modifier
                        .size(44.dp)
                        .clickable {
                            isBlackAndWhite1.value = true
                            isBlackAndWhite2.value = false
                            isBlackAndWhite3.value = true
                            sp.edit().putInt(Displays.AVATAR, 2).apply()

                        }
                )

                Text(
                    text = "Hero 2",
                    color = Color.White,
                    fontFamily = myFont,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Row (modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)){
                Image(
                    painter = painterResource(id = R.drawable.el6),
                    contentDescription = "3",
                    colorFilter = if (isBlackAndWhite3.value) ColorFilter.colorMatrix(matrix) else ColorFilter.colorMatrix(
                        ColorMatrix()
                    ),
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            isBlackAndWhite1.value = true
                            isBlackAndWhite2.value = true
                            isBlackAndWhite3.value = false
                            sp.edit().putInt(Displays.AVATAR, 3).apply()
                        }
                )

                Text(
                    text = "Hero 3",
                    color = Color.White,
                    fontFamily = myFont,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

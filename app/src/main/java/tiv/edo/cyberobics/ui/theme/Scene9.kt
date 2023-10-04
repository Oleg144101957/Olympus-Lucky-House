package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.navigation.NavHostController
import tiv.edo.cyberobics.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scene9(navigation: NavHostController) {

    //second name

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


    val context = LocalContext.current
    val sp = context.getSharedPreferences(Displays.PREFERENCES, Context.MODE_PRIVATE)
    val nameFromSP = sp.getString(Displays.SECONDNAME, "second name") ?: "second name"
    val myFont = FontFamily(Font(R.font.chibola))

    val name = remember {
        mutableStateOf(nameFromSP)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        TextField(
            value = name.value,
            modifier = Modifier
                .align(Alignment.Center),
            onValueChange = {
                val textAfterFilter = it.replace("\n", "")
                name.value = textAfterFilter
                sp.edit().putString(Displays.SECONDNAME, textAfterFilter).apply()
            }
        )

        Text(
            text = "Enter your second name",
            color = Color.White,
            fontFamily = myFont,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-64).dp)
        )

    }


}
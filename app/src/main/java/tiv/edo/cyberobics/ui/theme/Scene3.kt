package tiv.edo.cyberobics.ui.theme

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import tiv.edo.cyberobics.R
import kotlin.math.roundToInt

@Composable
fun Scene3(navigation: NavHostController) {
    //Play

    val myFont = FontFamily(Font(R.font.chibola))


    val a = LocalContext.current as Activity
    a.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val context = LocalContext.current
    val sp = context.getSharedPreferences(Displays.PREFERENCES, Context.MODE_PRIVATE)

    val nameFromShared = sp.getString(Displays.NAME, "user") ?: "user"
    val avaFromShared = sp.getInt(Displays.AVATAR, 1)
    val difficultLevel = sp.getFloat(Displays.DIFFICULT, 3000f)
    val secondName = sp.getString(Displays.SECONDNAME, "second name") ?: "second name"
    val win = sp.getInt(Displays.WIN, 0)

    val currentWin = remember {
        mutableIntStateOf(win)
    }


    val scaleFromSharedPref = sp.getFloat(Displays.SCALE, 1f)

    val currentScale = remember {
        mutableFloatStateOf(scaleFromSharedPref)
    }

    val score = remember {
        mutableIntStateOf(0)
    }

    val theHeightOfTheScreen = LocalConfiguration.current.screenHeightDp

    val fallingSpeed = remember {
        mutableIntStateOf(difficultLevel.toInt())
    }


    val ani1 = remember {
        Animatable(initialValue = 0f)
    }

    val ani2 = remember {
        Animatable(initialValue = 0f)
    }

    val ani3 = remember {
        Animatable(initialValue = 0f)
    }

    val ani4 = remember {
        Animatable(initialValue = 0f)
    }



    val elem1offsetX = remember {
        mutableStateOf(0f)
    }

    val elem1offsetY = remember {
        mutableStateOf(0f)
    }


    val elem2offsetX = remember {
        mutableStateOf(0f)
    }

    val elem2offsetY = remember {
        mutableStateOf(0f)
    }


    val elem3offsetX = remember {
        mutableStateOf(0f)
    }

    val elem3offsetY = remember {
        mutableStateOf(0f)
    }

    val elem4offsetX = remember {
        mutableStateOf(0f)
    }

    val elem4offsetY = remember {
        mutableStateOf(0f)
    }


    val baseOffsetX = remember {
        mutableStateOf(0f)
    }


    val baseOffsetXEndPoint = remember {
        mutableStateOf(0f)
    }

    val baseOffsetY = remember {
        mutableStateOf(0f)
    }


    val isVisible1 = remember {
        mutableStateOf(true)
    }

    val isVisible2 = remember {
        mutableStateOf(true)
    }

    val isVisible3 = remember {
        mutableStateOf(true)
    }

    val isVisible4 = remember {
        mutableStateOf(true)
    }

    val offsetX = remember { mutableStateOf(0f) }

    fun checkCatch1(){
        //check x and y
        if (elem1offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem1offsetY.value >= baseOffsetY.value){

            val newScore = score.value -10

            if (newScore>0){
                score.value = newScore
            }

            isVisible1.value = false
        }
    }

    fun checkCatch2(){
        //check x and y
        if (elem2offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem2offsetY.value >= baseOffsetY.value){
            score.value += 1
            isVisible2.value = false
        }
    }

    fun checkCatch3(){
        //check x and y
        if (elem3offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem3offsetY.value >= baseOffsetY.value){
            score.value += 1
            isVisible3.value = false
        }
    }

    fun checkCatch4(){
        //check x and y
        if (elem4offsetX.value in baseOffsetX.value..baseOffsetXEndPoint.value && elem4offsetY.value >= baseOffsetY.value){
            score.value += 3
            isVisible4.value = false
        }
    }

    LaunchedEffect(Unit){
        ani1.animateTo(
            targetValue = theHeightOfTheScreen.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 150, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani2.animateTo(
            targetValue = theHeightOfTheScreen.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani3.animateTo(
            targetValue = theHeightOfTheScreen.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 10, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani4.animateTo(
            targetValue = theHeightOfTheScreen.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(fallingSpeed.value, delayMillis = 250, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        repeat(64){
            delay(3000)
            isVisible1.value = true
            isVisible2.value = true
            isVisible3.value = true
            isVisible4.value = true
        }
    }



    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "start background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(32.dp)
                .clickable {
                    navigation.navigate(Displays.DisplayTwo.scene)
                }
        )


        if (isVisible1.value){
            Image(
                painter = painterResource(id = R.drawable.el2),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = ani1.value.dp, x = 128.dp)
                    .onGloballyPositioned {
                        elem1offsetY.value = it.positionInParent().y + it.size.height
                        elem1offsetX.value = it.positionInParent().x + it.size.width / 2
                        checkCatch1()
                    }
            )
        }

        if (isVisible2.value){
            Image(
                painter = painterResource(id = R.drawable.el3),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = ani2.value.dp, x = 64.dp)
                    .onGloballyPositioned {
                        elem2offsetY.value = it.positionInParent().y + it.size.height
                        elem2offsetX.value = it.positionInParent().x + it.size.width / 2
                        checkCatch2()
                    }
            )
        }


        if (isVisible3.value){
            Image(
                painter = painterResource(id = R.drawable.el4),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = ani3.value.dp, x = (-128).dp)
                    .onGloballyPositioned {
                        elem3offsetY.value = it.positionInParent().y + it.size.height
                        elem3offsetX.value = it.positionInParent().x + it.size.width / 2
                        checkCatch3()
                    }
            )
        }

        if (isVisible4.value){
            Image(
                painter = painterResource(id = R.drawable.el5),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = ani4.value.dp, x = (-64).dp)
                    .onGloballyPositioned {
                        elem4offsetY.value = it.positionInParent().y + it.size.height
                        elem4offsetX.value = it.positionInParent().x + it.size.width / 2
                        checkCatch4()
                    }
            )
        }




        Image(
            painter = painterResource(id = R.drawable.el9),
            contentDescription = "movable",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .scale(currentScale.value)
                .offset {
                    IntOffset(x = offsetX.value.roundToInt(), y = 0)
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offsetX.value += dragAmount.x
                    }
                }
                .onGloballyPositioned {
                    baseOffsetX.value = it.positionInParent().x
                    baseOffsetXEndPoint.value = it.positionInParent().x + it.size.width
                    baseOffsetY.value = it.positionInParent().y
                }

        )

        Text(
            text = "Your score is ${score.value}",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = myFont,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(8.dp)
        ) {
            val ava = when (avaFromShared){
                1 -> R.drawable.el1
                2 -> R.drawable.el2
                else -> R.drawable.el3

            }

            Row {
                Image(
                    painter = painterResource(id = ava),
                    contentDescription = "ava",
                    modifier = Modifier
                        .size(48.dp)
                )

                Column {

                    Text(
                        text = nameFromShared,
                        color = Color.White,
                        fontSize = 12.sp,
                    )

                    Text(
                        text = secondName,
                        color = Color.White,
                        fontSize = 12.sp,
                    )
                }
            }

            Row {
                if (win<4){
                    repeat(win){
                        Image(
                            painter = painterResource(id = R.drawable.el8),
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.el7),
                        contentDescription = "big winner",
                        modifier = Modifier.size(32.dp)
                    )
                    Text(text = "You win $win times", color = Color.White, fontSize = 12.sp)
                }
            }
        }
        
        
        if (score.value > 20){

            val newWin = currentWin.value + 1
            sp.edit().putInt(Displays.WIN, newWin).apply()

            Box(modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painterResource(id = R.drawable.bg2),
                    contentDescription = "win",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = "You win !!! Your score is 20",
                    color = Color.Yellow,
                    fontFamily = myFont,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Win button",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                        .size(64.dp)
                        .clickable {
                            navigation.navigate(Displays.DisplayTwo.scene)
                        }
                )
            }
        }
    }
}
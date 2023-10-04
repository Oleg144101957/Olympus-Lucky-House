package tiv.edo.cyberobics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import tiv.edo.cyberobics.ui.theme.NavHubMainFun
import tiv.edo.cyberobics.ui.theme.OlympusLuckyHouseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OlympusLuckyHouseTheme {
                // A surface container using the 'background' color from the theme
                NavHubMainFun()
            }
        }
    }
}

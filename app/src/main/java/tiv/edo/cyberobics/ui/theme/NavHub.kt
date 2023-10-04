package tiv.edo.cyberobics.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NavHubMainFun(){

    val navigation = rememberNavController()

    NavHost(navController = navigation, startDestination = Displays.DisplayOne.scene){

        composable(route = Displays.DisplayOne.scene){
            Scene1(navigation = navigation)
        }

        composable(route = Displays.DisplayTwo.scene){
            Scene2(navigation = navigation)
        }

        composable(route = Displays.DisplayThree.scene){
            Scene3(navigation = navigation)
        }

        composable(route = Displays.DisplayFour.scene){
            Scene4(navigation = navigation)
        }

        composable(route = Displays.DisplayFive.scene){
            Scene5(navigation = navigation)
        }

        composable(route = Displays.DisplaySix.scene){
            Scene6(navigation = navigation)
        }

        composable(route = Displays.DisplaySeven.scene){
            Scene7(navigation = navigation)
        }

        composable(route = Displays.DisplayEight.scene){
            Scene8(navigation = navigation)
        }

        composable(route = Displays.DisplayNine.scene){
            Scene9(navigation = navigation)
        }

        composable(route = Displays.DisplayTen.scene){
            Scene10(navigation = navigation)
        }

        composable(route = Displays.DisplayEleven.scene){
            Scene11(navigation = navigation)
        }

        composable(route = Displays.DisplayTwelve.scene){
            Scene12(navigation = navigation)
        }
    }
}
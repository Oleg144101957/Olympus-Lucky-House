package tiv.edo.cyberobics.ui.theme

sealed class Displays(val scene: String){

    object DisplayOne : Displays("display_one")
    object DisplayTwo : Displays("display_two")
    object DisplayThree : Displays("display_three")
    object DisplayFour : Displays("display_four")
    object DisplayFive : Displays("display_five")
    object DisplaySix : Displays("display_six")
    object DisplaySeven : Displays("display_seven")
    object DisplayEight : Displays("display_eight")
    object DisplayNine : Displays("display_nine")
    object DisplayTen : Displays("display_ten")
    object DisplayEleven : Displays("display_eleven")
    object DisplayTwelve : Displays("display_twelve")


    companion object{
        const val PREFERENCES = "PREFERENCES"
        const val NAME = "NAME"
        const val SECONDNAME = "SECONDNAME"
        const val DIFFICULT = "DIFFICULT"
        const val AVATAR = "AVATAR"
        const val SCALE = "SCALE"
        const val WIN = "WIN"
    }

}

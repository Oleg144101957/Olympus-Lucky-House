package tiv.edo.cyberobics.domain.models

import tiv.edo.cyberobics.OlympConstants

data class OlympusStatateOfHouse(
    val facebook: String = OlympConstants.initial_data,
    val apps: MutableMap<String, Any>?,
    val gaid: String = OlympConstants.initial_data
)
package com.krishisetu.model

data class SoilData(
    val ph: Double,
    val n: Int,
    val p: Int,
    val k: Int,
    val moisture: Double,
    val ec: Double,
    val status: String
)

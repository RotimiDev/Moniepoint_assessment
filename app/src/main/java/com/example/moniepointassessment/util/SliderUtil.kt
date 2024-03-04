package com.example.moniepointassessment.util

fun getSlidePosition(
    sliderWidth: Int,
    screenWidth: Int,
    positionOfMenuItem: Int
): Int {
    return when (positionOfMenuItem) {
        0 -> 0
        1 -> screenWidth / 4
        2 -> ( screenWidth / 4 ) * 2
        else -> screenWidth - sliderWidth
    }
}
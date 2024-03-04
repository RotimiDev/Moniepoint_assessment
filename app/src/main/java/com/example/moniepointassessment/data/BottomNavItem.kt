package com.example.moniepointassessment.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.moniepointassessment.R
import com.example.moniepointassessment.util.Route

sealed class BottomNavItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val imageId: Int
) {
    data object Home : BottomNavItem(Route.HOME, R.string.home, R.drawable.ic_home)
    data object Calculate : BottomNavItem(Route.CALCULATE, R.string.calculate, R.drawable.calculate)
    data object Complete : BottomNavItem(Route.COMPLETE, R.string.complete, 0)
    data object Shipment : BottomNavItem(Route.SHIPMENT, R.string.shipment, R.drawable.ic_device_reset)
    data object Profile: BottomNavItem("", R.string.profile, R.drawable.ic_profile)
}
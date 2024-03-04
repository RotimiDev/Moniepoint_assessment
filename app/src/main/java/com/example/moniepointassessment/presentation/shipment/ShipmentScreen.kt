package com.example.moniepointassessment.presentation.shipment

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun ShipmentScreen(onBackPressed: () -> Unit = {}, selectedMenuItem: Int = 0) {
    Column {
        MenuItemView(onBackPressed)
        ContentView(selectedMenuItem = selectedMenuItem)
    }
}
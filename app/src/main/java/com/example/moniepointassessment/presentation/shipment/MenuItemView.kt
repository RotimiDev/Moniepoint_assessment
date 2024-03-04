package com.example.moniepointassessment.presentation.shipment

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moniepointassessment.R

private val menuItems = arrayListOf(
    Pair(R.string.all, 12),
    Pair(R.string.completed, 5),
    Pair(R.string.progress, 3),
    Pair(R.string.pending, 4),
    Pair(R.string.cancelled, 0),
)

@Composable
fun MenuItemView(onBackPressed: () -> Unit = {}) {
    var positionOfSelectedMenuItem by remember { mutableIntStateOf(0) }
    Column {
        Header(onBackPressed)
        Navigation(positionOfSelectedMenuItem) {
            positionOfSelectedMenuItem = it
        }
        ContentView(selectedMenuItem = positionOfSelectedMenuItem)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Header(onBackPressed: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.purple_light))
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier.clickable(onClick = onBackPressed),
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = ""
        )
        Text(
            color = colorResource(id = R.color.white),
            text = stringResource(id = R.string.shipment_history),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string._title),
            color = colorResource(id = R.color.purple_light)
        )
    }
}

@Composable
private fun Navigation(positionOfSelectedMenuItem: Int, onMenuItemClicked: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .background(colorResource(id = R.color.purple_light))
            .padding(vertical = 0.dp, horizontal = 8.dp)
    ) {
        menuItems.forEachIndexed { index, menuItem ->
            NavigationItem(
                index,
                positionOfSelectedMenuItem,
                menuItem.first,
                menuItem.second
            ) { position ->
                // Call the onMenuItemClicked callback when a menu item is clicked
                onMenuItemClicked(position)
            }
        }
    }
}

@Composable
private fun NavigationItem(
    positionOnMenu: Int,
    positionOfSelectedMenuItem: Int,
    @StringRes title: Int,
    numberText: Int,
    onClicked: (position: Int) -> Unit
) {
    val isSelected = positionOnMenu == positionOfSelectedMenuItem

    Column(
        modifier = Modifier.clickable {
            onClicked(positionOnMenu)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSelected) colorResource(id = R.color.white)
                else colorResource(id = R.color.white_dull),
                text = stringResource(id = title)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (isSelected) colorResource(id = R.color.orange_dark)
                    else colorResource(id = R.color.light_blue_pale)
                )
                .padding(horizontal = 8.dp, vertical = .5.dp)) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = numberText.toString(),
                    color = colorResource(id = R.color.white)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .background(
                    if (isSelected) colorResource(id = R.color.orange_dark)
                    else colorResource(id = R.color.purple_light)
                )
                .size(60.dp, 4.dp)
        )
    }
}
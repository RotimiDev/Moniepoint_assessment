package com.example.moniepointassessment.presentation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moniepointassessment.R
import com.example.moniepointassessment.data.BottomNavItem
import com.example.moniepointassessment.util.getSlidePosition
import kotlin.math.roundToInt

private val menuItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Calculate,
    BottomNavItem.Shipment,
    BottomNavItem.Profile
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNav(modifier: Modifier = Modifier, onMenuItemClicked: (String) -> Unit = {}) {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val sliderWidth = (screenWidth / 4).dp
    var slideTo by remember { mutableIntStateOf(0) }

    Column(modifier) {

        Slider(sliderWidth, slideTo)

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(menuItems.size) {
                MenuItem(menuItems[it].imageId, menuItems[it].title) {
                    slideTo = getSlidePosition(sliderWidth.value.toInt(), screenWidth, it)
                    onMenuItemClicked(menuItems[it].route)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Slider(sliderWidth: Dp = 10.dp, destinationXAxis: Int = 0) {

    val destinationAlongTheXAxisToMoveTo = with(LocalDensity.current) {
        destinationXAxis.dp.toPx().roundToInt()
    }

    val offset by animateIntOffsetAsState(
        targetValue = IntOffset(destinationAlongTheXAxisToMoveTo, 0),
        label = "offset"
    )

    Box(
        modifier = Modifier
            .offset { offset }
            .background(colorResource(id = R.color.purple_light))
            .size(sliderWidth, 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MenuItem(
    @DrawableRes imageId: Int = R.drawable.ic_home,
    @StringRes title: Int = R.string.home,
    onClick: () -> Unit = {}
) {
    var hasBeenSelected by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            hasBeenSelected = !hasBeenSelected
            onClick()
        }
    ) {
        Image(painter = painterResource(id = imageId), contentDescription = "")

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.testTag(stringResource(id = title)),
            color = if (hasBeenSelected) colorResource(id = R.color.purple_light) else colorResource(
                id = R.color.default_ash
            ),
            fontSize = 12.sp,
            text = stringResource(id = title)
        )
    }
}

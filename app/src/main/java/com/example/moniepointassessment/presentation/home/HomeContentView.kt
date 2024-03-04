package com.example.moniepointassessment.presentation.home


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContent(modifier: Modifier = Modifier) {

    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(id = R.string.tracking)
        )
        Spacer(Modifier.height(16.dp))
        MainCard()
        Spacer(Modifier.height(24.dp))
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(id = R.string.available_vehicles)
        )
        Spacer(Modifier.height(16.dp))
        SubCards()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainCard() {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = .5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        color = colorResource(id = R.color.brown),
                        style = MaterialTheme.typography.bodyMedium,
                        text = stringResource(id = R.string.shipment_number)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = stringResource(id = R.string.receipt_number)
                    )
                }

                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_forklift),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                color = colorResource(id = R.color.light_brown),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(.5.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Sender()

            Spacer(modifier = Modifier.height(24.dp))

            Receiver()

            Spacer(modifier = Modifier.height(16.dp))

            Divider(
                color = colorResource(id = R.color.light_brown),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(.5.dp))

            Spacer(modifier = Modifier.height(16.dp))

            AddButton()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Sender() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.compass),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(
                    color = colorResource(id = R.color.brown),
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(id = R.string.sender)
                )
                Text(
                    text = stringResource(id = R.string.sender_address),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Column {
            Text(
                color = colorResource(id = R.color.brown),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(id = R.string.time)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .size(8.dp)
                        .background(colorResource(id = R.color.green))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.time_value),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Receiver() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.compass),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(
                    color = colorResource(id = R.color.brown),
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(id = R.string.receiver)
                )
                Text(
                    text = "Chicago, 6342",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Column {
            Text(
                color = colorResource(id = R.color.brown),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(id = R.string.status)
            )
            Text(
                text = "Waiting to collect",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AddButton() {
    Row {
        Image(painter = painterResource(id = R.drawable.add), contentDescription = "")
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "Add Stop",
            color = colorResource(id = R.color.orange_dark)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SubCards() {

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        SubCard()
        SubCard(R.string.vehicle_title_2, R.string.vehicle_desc_2, R.drawable.ic_cargo_truck)
        SubCard(R.string.vehicle_title_3, R.string.vehicle_desc_2, R.drawable.ic_air_shipping)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SubCard(@StringRes title: Int = R.string.vehicle_title_1,
                    @StringRes description: Int = R.string.vehicle_desc_1,
                    @DrawableRes image: Int = R.drawable.ic_cargo_ship) {

    var showAnimations by remember { mutableStateOf(false) }
    SideEffect { showAnimations = true }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = .5.dp)
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.white))
                .padding(16.dp)
        ) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(id = title)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                color = colorResource(id = R.color.brown),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(id = description)
            )
            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = showAnimations,
                enter = slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearEasing
                    )
                )
            ) {
                Image(
                    modifier = Modifier.size(130.dp, 150.dp),
                    painter = painterResource(id = image),
                    contentDescription = ""
                )
            }
        }
    }

}
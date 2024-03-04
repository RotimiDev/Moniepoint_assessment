package com.example.moniepointassessment.presentation.calculate

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moniepointassessment.R
import com.example.moniepointassessment.data.model.Destination
import com.example.moniepointassessment.util.bouncyClickable

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculateView(onClick: () -> Unit = {}, onBackPressed: () -> Unit = {}) {
    Column {
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
                text = stringResource(id = R.string.calculate),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(id = R.string._title),
                color = colorResource(id = R.color.purple_light)
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MainContent()
            CalculateButton(onClick)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainContent() {
    val destinations = arrayOf(
        Destination(R.drawable.outline_present_to_all_24, R.string.sender_location),
        Destination(R.drawable.outline_present_to_all_24, R.string.receiver_location),
        Destination(R.drawable.baseline_hourglass_empty, R.string.approx_weight)
    )

    Column() {
        Text(
            text = stringResource(id = R.string.destination),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(16.dp))

        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = .5.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.white))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(3) { Details(destinations[it].drawable, destinations[it].title) }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.text_packaging),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string.question),
            color = colorResource(id = R.color.brown),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        PackagingBox()

        Spacer(Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.categories),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string.question),
            color = colorResource(id = R.color.brown),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        Categories()

        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Details(
    @DrawableRes drawable: Int = R.drawable.outline_present_to_all_24,
    @StringRes title: Int = R.string.calculate
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.very_light_brown))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(painter = painterResource(drawable), contentDescription = "")

        Spacer(Modifier.width(8.dp))

        Divider(
            color = colorResource(id = R.color.light_brown),
            modifier = Modifier
                .width(1.dp)
                .height(24.dp)
        )

        Spacer(Modifier.width(8.dp))

        val text = remember { mutableStateOf("") }
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text.value,
            onValueChange = {
                text.value = it
            },
            decorationBox = { innerTextField ->
                Box {
                    if (text.value.isEmpty()) {
                        Text(
                            text = stringResource(id = title),
                            color = colorResource(id = R.color.brown),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PackagingBox() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = .5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_box),
                    contentDescription = ""
                )

                Spacer(Modifier.width(8.dp))

                Divider(
                    color = colorResource(id = R.color.light_brown),
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.box),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Image(painter = painterResource(id = R.drawable.arrow_down), contentDescription = "")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Categories() {
    var showAnimations by remember { mutableStateOf(false) }

    SideEffect { showAnimations = true }

    val categories = arrayOf(
        R.string.documents,
        R.string.glass,
        R.string.liquid,
        R.string.food,
        R.string.electronic,
        R.string.product,
        R.string.others
    )

    AnimatedVisibility(
        visible = showAnimations,
        enter = slideInHorizontally(
            initialOffsetX = { 300 }, // small slide 300px
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing // interpolator
            )
        )
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            repeat(7) { Category(categories[it]) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Category(@StringRes text: Int = R.string.categories) {
    var isClicked by remember { mutableStateOf(false) }
    val buttonTextColor = if (isClicked) R.color.white else R.color.brown
    val buttonBodyColor = if (isClicked) colorResource(id = R.color.black) else Color.Transparent

    OutlinedButton(
        modifier = Modifier.bouncyClickable {},
        onClick = {
            isClicked = !isClicked
        },
        border = BorderStroke(1.dp, colorResource(id = R.color.brown)),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = buttonBodyColor,
            contentColor = colorResource(id = R.color.brown)
        )
    ) {
        Row {
            if (isClicked) {
                Image(painter = painterResource(id = R.drawable.check), contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = buttonTextColor)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CalculateButton(onClick: () -> Unit = {}) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .bouncyClickable(onClick),
        contentPadding = PaddingValues(all = 14.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange)),
    ) {
        Text(
            text = stringResource(id = R.string.calculate),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
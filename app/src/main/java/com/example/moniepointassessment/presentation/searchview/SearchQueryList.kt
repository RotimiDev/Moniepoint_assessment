package com.example.moniepointassessment.presentation.searchview

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun SearchQueryList(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = .5.dp)
    ) {
        Column(Modifier.background(colorResource(id = R.color.white))) {
            SearchedItem(R.string.title_1, R.string.number_, R.string.paris, R.string.morocco)
            SearchedItem(R.string.title_2, R.string.number_, R.string.barcelona, R.string.paris)
            SearchedItem(R.string.title_3, R.string.number_, R.string.colombia, R.string.paris)
            SearchedItem(R.string.title_4, R.string.number_, R.string.bogota, R.string.dhaka)
            SearchedItem(R.string.title_5, R.string.number_, R.string.france, R.string.germany)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchedItem(
    @StringRes title: Int = R.string.title_1,
    @StringRes receiptNumber: Int = R.string.receipt_number,
    @StringRes from: Int = R.string.berlin,
    @StringRes destination: Int = R.string.bogota
) {
    Row(Modifier.padding(16.dp)) {
        Box(
            Modifier
                .clip(CircleShape)
                .background(colorResource(id = R.color.purple_light))
                .size(48.dp)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_box),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(id = title)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    color = colorResource(id = R.color.brown),
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(id = receiptNumber)
                )

                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    Modifier
                        .size(4.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.default_ash))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    color = colorResource(id = R.color.brown),
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(id = from)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.long_arrow),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    color = colorResource(id = R.color.brown),
                    style = MaterialTheme.typography.bodyMedium,
                    text = stringResource(id = destination)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                color = colorResource(id = R.color.light_brown),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(.5.dp)
            )
        }
    }
}
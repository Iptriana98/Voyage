package com.iptriana.voyage.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iptriana.voyage.R
import com.iptriana.voyage.ui.theme.VoyageTheme

private val screens = listOf("Find Trips", "My Trips", "Saved Trips", "Price Alerts", "My Account")

@Composable
fun VoyageDrawer(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_crane_drawer),
            contentDescription = stringResource(R.string.cd_drawer)
        )
        for (screen in screens) {
            Spacer(Modifier.height(24.dp))
            Text(text = screen, style = MaterialTheme.typography.headlineLarge)
        }
    }
}

@Preview
@Composable
fun VoyageDrawerPreview() {
    VoyageTheme {
        VoyageDrawer()
    }
}

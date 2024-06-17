package com.iptriana.voyage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.iptriana.voyage.ui.details.launchDetailsActivity
import com.iptriana.voyage.ui.home.MainViewModel
import com.iptriana.voyage.ui.home.OnExploreItemClicked
import com.iptriana.voyage.ui.home.VoyageHome
import com.iptriana.voyage.ui.theme.VoyageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            VoyageTheme {
                val viewModel: MainViewModel by viewModels()
                MainScreen(viewModel = viewModel, onExploreItemClicked = {
                    launchDetailsActivity(
                        context = this,
                        item = it
                    )
                })
            }
        }
    }
}

@Composable
private fun MainScreen(onExploreItemClicked: OnExploreItemClicked, viewModel: MainViewModel) {
    Surface(color = MaterialTheme.colorScheme.primary) {
//        var showLandingScreen by remember { mutableStateOf(true) }
//        if (showLandingScreen) {
//            LandingScreen(onTimeout = { showLandingScreen = false })
//        } else {
            VoyageHome(onExploreItemClicked = onExploreItemClicked)
//        }
    }
}
package com.iptriana.voyage.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iptriana.voyage.data.ExploreModel
import com.iptriana.voyage.ui.composables.ExploreSection
import com.iptriana.voyage.ui.composables.VoyageDrawer
import com.iptriana.voyage.ui.composables.VoyageTabBar
import com.iptriana.voyage.ui.composables.VoyageTabs
import kotlinx.coroutines.launch

typealias OnExploreItemClicked = (ExploreModel) -> Unit

enum class VoyageScreen {
    Fly, Sleep, Eat
}

@Composable
fun VoyageHome(
    onExploreItemClicked: OnExploreItemClicked,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                VoyageDrawer()
            }
        }
    ) {
        Scaffold { paddingValues ->
            VoyageHomeContent(
                modifier = Modifier.padding(paddingValues),
                onExploreItemClicked = onExploreItemClicked,
                openDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    }
}

@Composable
fun VoyageHomeContent(
    onExploreItemClicked: OnExploreItemClicked,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val suggestedDestinations by viewModel.suggestedDestinations.collectAsStateWithLifecycle()

    val onPeopleChanged: (Int) -> Unit = { viewModel.updatePeople(it) }
    var tabSelected by remember { mutableStateOf(VoyageScreen.Fly) }

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        drawerContent = {
            ModalDrawerSheet {
                SearchContent(
                    tabSelected = tabSelected,
                    onPeopleChanged = onPeopleChanged,
                )
            }
        }
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                HomeTabBar(openDrawer, tabSelected, onTabSelected = { tabSelected = it })
            }
        ) { paddingValues ->
            when (tabSelected) {
                VoyageScreen.Fly -> {
                    ExploreSection(
                        title = "Explore Flights by Destination",
                        exploreList = suggestedDestinations,
                        onItemClicked = onExploreItemClicked,
                        modifier = Modifier.padding(paddingValues)
                    )
                }

                VoyageScreen.Sleep -> {
                    ExploreSection(
                        title = "Explore Properties by Destination",
                        exploreList = viewModel.hotels,
                        onItemClicked = onExploreItemClicked,
                        modifier = Modifier.padding(paddingValues)
                    )
                }

                VoyageScreen.Eat -> {
                    ExploreSection(
                        title = "Explore Restaurants by Destination",
                        exploreList = viewModel.restaurants,
                        onItemClicked = onExploreItemClicked,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeTabBar(
    openDrawer: () -> Unit,
    tabSelected: VoyageScreen,
    onTabSelected: (VoyageScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    VoyageTabBar(
        modifier = modifier,
        onMenuClicked = openDrawer
    ) { tabBarModifier ->
        VoyageTabs(
            modifier = tabBarModifier,
            titles = VoyageScreen.values().map { it.name },
            tabSelected = tabSelected,
            onTabSelected = { newTab -> onTabSelected(VoyageScreen.values()[newTab.ordinal]) }
        )
    }
}

@Composable
private fun SearchContent(
    tabSelected: VoyageScreen,
    viewModel: MainViewModel = viewModel(),
    onPeopleChanged: (Int) -> Unit
) {
    when (tabSelected) {
        VoyageScreen.Fly -> FlySearchContent(
            onPeopleChanged = onPeopleChanged,
            onToDestinationChanged = { viewModel.toDestinationChanged(it) }
        )

        VoyageScreen.Sleep -> SleepSearchContent(
            onPeopleChanged = onPeopleChanged
        )

        VoyageScreen.Eat -> EatSearchContent(
            onPeopleChanged = onPeopleChanged
        )
    }
}

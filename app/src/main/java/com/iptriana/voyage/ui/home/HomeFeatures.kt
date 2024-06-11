package com.iptriana.voyage.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iptriana.voyage.R
import com.iptriana.voyage.ui.composables.DatesUserInput
import com.iptriana.voyage.ui.composables.FromDestination
import com.iptriana.voyage.ui.composables.PeopleUserInput
import com.iptriana.voyage.ui.composables.SimpleUserInput
import com.iptriana.voyage.ui.composables.ToDestinationUserInput


@Composable
fun FlySearchContent(
    onPeopleChanged: (Int) -> Unit,
    onToDestinationChanged: (String) -> Unit
) {
    CraneSearch {
        PeopleUserInput(
            titleSuffix = ", Economy",
            onPeopleChanged = onPeopleChanged
        )
        Spacer(Modifier.height(8.dp))
        FromDestination()
        Spacer(Modifier.height(8.dp))
        ToDestinationUserInput(onToDestinationChanged = onToDestinationChanged)
        Spacer(Modifier.height(8.dp))
        DatesUserInput()
    }
}

@Composable
fun SleepSearchContent(onPeopleChanged: (Int) -> Unit) {
    CraneSearch {
        PeopleUserInput(onPeopleChanged = onPeopleChanged)
        Spacer(Modifier.height(8.dp))
        DatesUserInput()
        Spacer(Modifier.height(8.dp))
        SimpleUserInput(caption = "Select Location", vectorImageId = R.drawable.ic_hotel)
    }
}

@Composable
fun EatSearchContent(onPeopleChanged: (Int) -> Unit) {
    CraneSearch {
        PeopleUserInput(onPeopleChanged = onPeopleChanged)
        Spacer(Modifier.height(8.dp))
        DatesUserInput()
        Spacer(Modifier.height(8.dp))
        SimpleUserInput(caption = "Select Time", vectorImageId = R.drawable.ic_time)
        Spacer(Modifier.height(8.dp))
        SimpleUserInput(caption = "Select Location", vectorImageId = R.drawable.ic_restaurant)
    }
}

@Composable
private fun CraneSearch(content: @Composable () -> Unit) {
    Column(Modifier.padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 12.dp)) {
        content()
    }
}
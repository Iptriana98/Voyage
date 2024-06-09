package com.iptriana.voyage.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iptriana.voyage.data.DestinationsRepository
import com.iptriana.voyage.data.ExploreModel
import com.iptriana.voyage.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val destinationsRepository: DestinationsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val cityName = savedStateHandle.get<String>(KEY_ARG_DETAILS_CITY_NAME)!!

    val cityDetails: Result<ExploreModel>
        get() {
            val destination = destinationsRepository.getDestination(cityName)
            return if (destination != null) {
                Result.Success(destination)
            } else {
                Result.Error(IllegalArgumentException("City doesn't exist"))
            }
        }
}

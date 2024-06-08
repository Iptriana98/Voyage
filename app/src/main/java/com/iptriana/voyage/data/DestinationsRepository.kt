package com.iptriana.voyage.data

import javax.inject.Inject

class DestinationsRepository @Inject constructor(
    private val destinationsLocalDataSource: DestinationsLocalDataSource
) {
    val destinations: List<ExploreModel> = destinationsLocalDataSource.voyageDestinations
    val hotels: List<ExploreModel> = destinationsLocalDataSource.voyageHotels
    val restaurants: List<ExploreModel> = destinationsLocalDataSource.voyageRestaurants

    fun getDestination(cityName: String): ExploreModel? {
        return destinationsLocalDataSource.voyageDestinations.firstOrNull {
            it.city.name == cityName
        }
    }
}

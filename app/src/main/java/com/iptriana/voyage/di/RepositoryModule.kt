package com.iptriana.voyage.di

import com.iptriana.voyage.data.DestinationsLocalDataSource
import com.iptriana.voyage.data.DestinationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideDestinationsRepository(destinationsLocalDataSource: DestinationsLocalDataSource): DestinationsRepository =
        DestinationsRepository(destinationsLocalDataSource)
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideDestinationsLocalDataSource(): DestinationsLocalDataSource =
        DestinationsLocalDataSource()
}
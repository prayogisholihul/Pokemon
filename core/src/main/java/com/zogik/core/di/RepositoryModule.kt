package com.zogik.core.di

import com.zogik.core.data.detail.data_source.local.DetailLocalDataSource
import com.zogik.core.data.detail.repository.DetailRepositoryImpl
import com.zogik.core.data.home.data_source.remote.HomeRemoteDataSource
import com.zogik.core.data.home.repository.HomeRepositoryImpl
import com.zogik.core.data.my_pokemon.data_source.local.MyPokemonLocalDataSource
import com.zogik.core.data.my_pokemon.repository.MyPokemonRepositoryImpl
import com.zogik.core.domain.detail.repository.DetailRepository
import com.zogik.core.domain.home.repository.HomeRepository
import com.zogik.core.domain.my_pokemon.repository.MyPokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(homeRemoteDataSource: HomeRemoteDataSource): HomeRepository {
        return HomeRepositoryImpl(homeRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(detailLocalDataSource: DetailLocalDataSource): DetailRepository {
        return DetailRepositoryImpl(detailLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideMyPokemonRepository(
        myPokemonLocalDataSource: MyPokemonLocalDataSource
    ): MyPokemonRepository {
        return MyPokemonRepositoryImpl(myPokemonLocalDataSource)
    }
}

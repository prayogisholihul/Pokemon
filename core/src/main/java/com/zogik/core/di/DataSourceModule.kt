package com.zogik.core.di

import com.zogik.core.data.detail.data_source.local.DetailLocalDataSource
import com.zogik.core.data.detail.data_source.local.DetailLocalDataSourceImpl
import com.zogik.core.data.detail.data_source.local.dao.DetailDao
import com.zogik.core.data.home.data_source.remote.HomeRemoteDataSource
import com.zogik.core.data.home.data_source.remote.HomeRemoteDataSourceImpl
import com.zogik.core.data.home.data_source.remote.service.HomeService
import com.zogik.core.data.my_pokemon.data_source.local.MyPokemonLocalDataSource
import com.zogik.core.data.my_pokemon.data_source.local.MyPokemonLocalDataSourceImpl
import com.zogik.core.data.my_pokemon.data_source.local.dao.MyPokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideHomeRemoteDataSource(homeService: HomeService): HomeRemoteDataSource {
        return HomeRemoteDataSourceImpl(homeService)
    }

    @Provides
    @Singleton
    fun provideDetailLocalDataSource(detailDao: DetailDao): DetailLocalDataSource {
        return DetailLocalDataSourceImpl(detailDao)
    }

    @Provides
    @Singleton
    fun provideMyPokemonLocalDataSource(myPokemonDao: MyPokemonDao): MyPokemonLocalDataSource {
        return MyPokemonLocalDataSourceImpl(myPokemonDao)
    }
}

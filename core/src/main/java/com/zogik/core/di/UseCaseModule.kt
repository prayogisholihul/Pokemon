package com.zogik.core.di

import com.zogik.core.domain.home.use_case.GetPokemonListUseCase
import com.zogik.core.domain.detail.repository.DetailRepository
import com.zogik.core.domain.detail.use_case.CatchPokemonUseCase
import com.zogik.core.domain.detail.use_case.GetSpecificPokemonUseCase
import com.zogik.core.domain.home.repository.HomeRepository
import com.zogik.core.domain.home.use_case.GetCompletePokemonDataUseCase
import com.zogik.core.domain.my_pokemon.repository.MyPokemonRepository
import com.zogik.core.domain.my_pokemon.use_case.DeletePokemonUseCase
import com.zogik.core.domain.my_pokemon.use_case.GetMyPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(
        homeRepository: HomeRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): GetPokemonListUseCase {
        return GetPokemonListUseCase(homeRepository, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideGetSpecificPokemonUseCase(
        homeRepository: HomeRepository,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): GetSpecificPokemonUseCase {
        return GetSpecificPokemonUseCase(homeRepository, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideGetCompletePokemonDataUseCase(
        getSpecificPokemonUseCase: GetSpecificPokemonUseCase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): GetCompletePokemonDataUseCase {
        return GetCompletePokemonDataUseCase(
            getSpecificPokemonUseCase,
            ioDispatcher
        )
    }

    @Provides
    @Singleton
    fun provideGetMyPokemonListUseCase(
        myPokemonRepository: MyPokemonRepository,
    ): GetMyPokemonListUseCase {
        return GetMyPokemonListUseCase(myPokemonRepository)
    }

    @Provides
    @Singleton
    fun provideCatchPokemonUseCase(detailRepository: DetailRepository): CatchPokemonUseCase {
        return CatchPokemonUseCase(detailRepository)
    }

    @Provides
    @Singleton
    fun provideDeletePokemonUseCase(
        myPokemonRepository: MyPokemonRepository
    ): DeletePokemonUseCase {
        return DeletePokemonUseCase(myPokemonRepository)
    }
}

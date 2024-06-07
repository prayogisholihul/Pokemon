package com.zogik.core.domain.my_pokemon.use_case

import com.zogik.core.domain.model.MyPokemon
import com.zogik.core.domain.my_pokemon.repository.MyPokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyPokemonListUseCase @Inject constructor(
    private val myPokemonRepository: MyPokemonRepository
) {
    operator fun invoke(): Flow<List<MyPokemon>> {
        return myPokemonRepository.getMyPokemonList()
    }
}

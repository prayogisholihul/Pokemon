package com.zogik.core.domain.my_pokemon.use_case

import com.zogik.core.domain.my_pokemon.repository.MyPokemonRepository
import javax.inject.Inject

class DeletePokemonUseCase @Inject constructor(
    private val myPokemonRepository: MyPokemonRepository
) {
    operator fun invoke(name: String) {
        myPokemonRepository.deletePokemon(name)
    }
}

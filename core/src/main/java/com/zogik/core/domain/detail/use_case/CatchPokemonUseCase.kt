package com.zogik.core.domain.detail.use_case

import com.zogik.core.domain.detail.repository.DetailRepository
import com.zogik.core.domain.model.MyPokemon
import javax.inject.Inject

class CatchPokemonUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    operator fun invoke(pokemon: MyPokemon) {
        detailRepository.catchPokemon(pokemon)
    }

    fun updateOwnedValue(name: String) {
        detailRepository.updateOwnedValue(name)
    }

    fun isPokemonExist(name: String): Boolean {
        return detailRepository.isPokemonExist(name)
    }
}

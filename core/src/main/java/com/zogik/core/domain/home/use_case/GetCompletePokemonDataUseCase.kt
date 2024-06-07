package com.zogik.core.domain.home.use_case

import com.zogik.core.di.IoDispatcher
import com.zogik.core.domain.model.Pokemon
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.domain.detail.use_case.GetSpecificPokemonUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCompletePokemonDataUseCase @Inject constructor(
    private val getSpecificPokemonUseCase: GetSpecificPokemonUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        pokemonList: List<Pokemon>,
        offset: Int,
        throwable: Throwable = Throwable()
    ): Flow<Result<List<SpecificPokemon>>> {
        return flow<Result<List<SpecificPokemon>>> {
            val completePokemonDataList = mutableListOf<SpecificPokemon>()

            pokemonList.map { pokemon ->
                getSpecificPokemonUseCase.invoke(pokemon.name).collectLatest {
                    when {
                        it.isSuccess -> {
                            completePokemonDataList.add(it.getOrNull() ?: SpecificPokemon())
                        }
                        it.isFailure -> {
                            completePokemonDataList.addAll(emptyList())
                        }

                        else -> {}
                    }
                }
            }

            if (offset == 0 && completePokemonDataList.isEmpty()) {
                return@flow emit(Result.failure(throwable))
            }
            emit(Result.success(completePokemonDataList))
        }.catch { e ->
            emit(Result.failure(e))
        }.flowOn(ioDispatcher)
    }
}

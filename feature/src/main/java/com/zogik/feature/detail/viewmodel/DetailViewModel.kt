package com.zogik.feature.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zogik.core.domain.detail.use_case.CatchPokemonUseCase
import com.zogik.core.domain.detail.use_case.GetSpecificPokemonUseCase
import com.zogik.core.domain.model.MyPokemon
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSpecificPokemonUseCase: GetSpecificPokemonUseCase,
    private val catchPokemonUseCase: CatchPokemonUseCase,
) : ViewModel() {
    private val _pokemonState: MutableStateFlow<Resource<SpecificPokemon>> =
        MutableStateFlow(Resource.Init())
    val pokemonState: MutableStateFlow<Resource<SpecificPokemon>> get() = _pokemonState

    var job: Job? = null

    init {
        initLoading()
    }

    private fun initLoading() {
        _pokemonState.value = Resource.Loading()
    }

    fun getPokemonByName(name: String) {
        job = viewModelScope.launch {
            getSpecificPokemonUseCase.invoke(name).cancellable().collectLatest { res ->
                when {
                    res.isSuccess -> {
                        _pokemonState.value = Resource.Success(res.getOrNull())
                    }

                    res.isFailure -> {
                        _pokemonState.value = Resource.Error(
                            res.exceptionOrNull().toString(),
                            _pokemonState.value.data
                        )
                    }
                }
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
    }

    fun catchPokemon(pokemon: MyPokemon) {
        if (!catchPokemonUseCase.isPokemonExist(pokemon.name)) {
            catchPokemonUseCase.invoke(pokemon)
        }
        catchPokemonUseCase.updateOwnedValue(pokemon.name)
    }
}

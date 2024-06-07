package com.zogik.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zogik.core.domain.home.use_case.GetPokemonListUseCase
import com.zogik.core.domain.home.use_case.GetCompletePokemonDataUseCase
import com.zogik.core.domain.model.LazyGridData
import com.zogik.core.domain.model.LoadingDataModel
import com.zogik.core.domain.model.PagingDataModel
import com.zogik.core.domain.model.SpecificPokemon
import com.zogik.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonCompletePokemonDataUseCase: GetCompletePokemonDataUseCase
) : ViewModel() {

    companion object {
        const val OFFSET = 20
    }

    private val _pokemonListState: MutableStateFlow<Resource<List<LazyGridData>>> =
        MutableStateFlow(Resource.Init())
    val pokemonListState: MutableStateFlow<Resource<List<LazyGridData>>>
        get() = _pokemonListState

    private val _pageState: MutableStateFlow<PagingDataModel> = MutableStateFlow(PagingDataModel())

    init {
        initLoading()
        getPokemonList()
    }

    fun loadNext() {
        showLoadingData()
        _pageState.update {
            it.copy(offset = it.offset?.plus(OFFSET))
        }
    }

    fun initLoading() {
        _pokemonListState.value = Resource.Loading()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _pageState.flatMapLatest {
                getPokemonListUseCase.invoke(it.offset ?: 0)
                    .flatMapLatest { pokemonListRes ->
                        when {
                            pokemonListRes.isSuccess -> {
                                getPokemonCompletePokemonDataUseCase.invoke(
                                        pokemonListRes.getOrNull() ?: emptyList(), it.offset ?: 0
                                        )
                            }
                            else -> {
                                getPokemonCompletePokemonDataUseCase.invoke(emptyList(), it.offset ?: 0, pokemonListRes.exceptionOrNull() ?: Throwable())
                            }
                        }
                    }
            }.collectLatest { res ->
                when {
                    res.isSuccess -> {
                        combineAllData(res.getOrNull() ?: listOf())
                    }
                    res.isFailure -> {
                        _pokemonListState.value = Resource.Error(
                            res.exceptionOrNull().toString(),
                            _pokemonListState.value.data
                        )
                    }
                }
            }
        }
    }

    private fun combineAllData(res: List<SpecificPokemon>) {
        _pokemonListState.update {
            var data = it.data.orEmpty().toMutableList()
            if (data.isNotEmpty() && data.last() is LoadingDataModel) {
                data = data.dropLast(1).toMutableList()
            }
            val finalResult = listOf(data, res).flatten()
            return@update Resource.Success(finalResult)
        }
    }

    private fun showLoadingData() {
        _pokemonListState.update {
            val data = it.data.orEmpty().toMutableList()
            if (data.last() !is LoadingDataModel) {
                data.add(LoadingDataModel())
            }
            return@update Resource.Success(data)
        }
    }
}

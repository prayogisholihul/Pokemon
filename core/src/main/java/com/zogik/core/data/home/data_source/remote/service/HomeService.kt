package com.zogik.core.data.home.data_source.remote.service

import com.zogik.core.data.home.data_source.remote.dto.PokemonResponse
import com.zogik.core.data.home.data_source.remote.dto.SpecificPokemonResponse
import com.zogik.core.util.UrlRoute.POKEMON_LIST_URL
import com.zogik.core.util.UrlRoute.SPECIFIC_POKEMON_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {

    companion object {
        const val LIMIT = 20
    }

    @GET(POKEMON_LIST_URL)
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = LIMIT
    ): Response<PokemonResponse>

    @GET(SPECIFIC_POKEMON_URL)
    suspend fun getSpecificPokemon(@Path("name") name: String): Response<SpecificPokemonResponse>
}

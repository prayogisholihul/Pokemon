package com.zogik.core.data.home.data_source.remote.dto

import com.zogik.core.domain.model.SpecificPokemon
import com.google.gson.annotations.SerializedName

data class SpecificPokemonResponse(

    @SerializedName("abilities")
    val abilities: List<AbilityDto> = listOf(),

    @SerializedName("base_experience")
    val baseExperience: Int = 0,

    @SerializedName("forms")
    val forms: List<PokemonDto> = listOf(),

    @SerializedName("game_indices")
    val gameIndices: List<GameIndiceDto> = listOf(),

    @SerializedName("height")
    val height: Int = 0,

    @SerializedName("held_items")
    val held_items: List<Any> = listOf(),

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("is_default")
    val isDefault: Boolean = false,

    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String = "",

    @SerializedName("moves")
    val moves: List<MoveDto> = listOf(),

    @SerializedName("name")
    val name: String = "",

    @SerializedName("order")
    val order: Int = 0,

    @SerializedName("past_types")
    val pastTypes: List<Any> = listOf(),

    @SerializedName("species")
    val species: PokemonDto = PokemonDto(),

    @SerializedName("sprites")
    val sprites: SpritesDto = SpritesDto(),

    @SerializedName("stats")
    val stats: List<StatDto> = listOf(),

    @SerializedName("types")
    val types: List<TypeDto> = listOf(),

    @SerializedName("weight")
    val weight: Int = 0
)

fun SpecificPokemonResponse.toModel() = SpecificPokemon(
    this.abilities.map { it.toModel() },
    this.baseExperience,
    this.forms.map { it.toModel() },
    this.gameIndices.map { it.toModel() },
    this.height,
    this.held_items,
    this.id,
    this.isDefault,
    this.locationAreaEncounters,
    this.moves.map { it.toModel() },
    this.name,
    this.order,
    this.pastTypes,
    this.species.toModel(),
    this.sprites.toModel(),
    this.stats.map { it.toModel() },
    this.types.map { it.toModel() },
    this.weight,
)

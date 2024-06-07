package com.zogik.core.domain.model

data class SpecificPokemon(
    val abilities: List<Ability> = listOf(),
    val baseExperience: Int = 0,
    val forms: List<Pokemon> = listOf(),
    val gameIndices: List<GameIndice> = listOf(),
    val height: Int = 0,
    val held_items: List<Any> = listOf(),
    override val id: Int = 0,
    val isDefault: Boolean = false,
    val locationAreaEncounters: String = "",
    val moves: List<Move> = listOf(),
    val name: String = "",
    val order: Int = 0,
    val pastTypes: List<Any> = listOf(),
    val species: Pokemon = Pokemon(),
    val sprites: Sprites = Sprites(),
    val stats: List<Stat> = listOf(),
    val types: List<Type> = listOf(),
    val weight: Int = 0
) : LazyGridData

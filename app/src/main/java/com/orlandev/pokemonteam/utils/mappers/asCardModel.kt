package com.orlandev.pokemonteam.utils.mappers

import com.orlandev.pokemonteam.ui.common.CardModel
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

fun NamedApiResource.asCardModel(): CardModel {
    return CardModel(
        title = this.name, subtitle = this.category, image = null
    )
}
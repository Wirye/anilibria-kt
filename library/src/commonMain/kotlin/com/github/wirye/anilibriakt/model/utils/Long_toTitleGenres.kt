package com.github.wirye.anilibriakt.model.utils

import com.github.wirye.anilibriakt.model.TitleGenres

fun Long.toTitleGenres(): TitleGenres? {
    return when (this) {
        1L -> TitleGenres.COMEDY
        2L -> TitleGenres.MECHA
        3L -> TitleGenres.PSYCHOLOGICAL
        4L -> TitleGenres.SHONEN
        5L -> TitleGenres.SEINEN
        6L -> TitleGenres.TRILLER
        7L -> TitleGenres.SCHOOL
        8L -> TitleGenres.DRAMA
        9L -> TitleGenres.MYSTERY
        10L -> TitleGenres.EVERYDAYLIFE
        11L -> TitleGenres.ROMANCE
        12L -> TitleGenres.SPORT
        13L -> TitleGenres.HORROR
        14L -> TitleGenres.ACTION
        15L -> TitleGenres.MARTIALARTS
        16L -> TitleGenres.DEMONS
        17L -> TitleGenres.GAMES
        18L -> TitleGenres.MAGIC
        19L -> TitleGenres.MUSIC
        20L -> TitleGenres.SHOUJO
        21L -> TitleGenres.SUPERPOWER
        22L -> TitleGenres.FANTASTIC
        23L -> TitleGenres.ETTY
        24L -> TitleGenres.VAMPIRES
        25L -> TitleGenres.DETECTIVE
        26L -> TitleGenres.HISTORICAL
        27L -> TitleGenres.ADVENTURES
        28L -> TitleGenres.MYSTICISM
        29L -> TitleGenres.FANTASY
        30L -> TitleGenres.CYBERPUNK
        31L -> TitleGenres.GIRLSLOVE
        32L -> TitleGenres.HAREM
        33L -> TitleGenres.JOSEI
        34L -> TitleGenres.ISEKAI
        else -> null
    }
}

package com.github.wirye.anilibriakt.model.utils

import com.github.wirye.anilibriakt.model.TitleGenres

fun TitleGenres.toLong(): Long {
    return when(this) {
        TitleGenres.COMEDY -> 1L
        TitleGenres.MECHA -> 2L
        TitleGenres.PSYCHOLOGICAL -> 3L
        TitleGenres.SHONEN -> 4L
        TitleGenres.SEINEN -> 5L
        TitleGenres.TRILLER -> 6L
        TitleGenres.SCHOOL -> 7L
        TitleGenres.DRAMA -> 8L
        TitleGenres.MYSTERY -> 9L
        TitleGenres.EVERYDAYLIFE -> 10L
        TitleGenres.ROMANCE -> 11L
        TitleGenres.SPORT -> 12L
        TitleGenres.HORROR -> 13L
        TitleGenres.ACTION -> 14L
        TitleGenres.MARTIALARTS -> 15L
        TitleGenres.DEMONS -> 16L
        TitleGenres.GAMES -> 17L
        TitleGenres.MAGIC -> 18L
        TitleGenres.MUSIC -> 19L
        TitleGenres.SHOUJO -> 20L
        TitleGenres.SUPERPOWER -> 21L
        TitleGenres.FANTASTIC -> 22L
        TitleGenres.ETTY -> 23L
        TitleGenres.VAMPIRES -> 24L
        TitleGenres.DETECTIVE -> 25L
        TitleGenres.HISTORICAL -> 26L
        TitleGenres.ADVENTURES -> 27L
        TitleGenres.MYSTICISM -> 28L
        TitleGenres.FANTASY -> 29L
        TitleGenres.CYBERPUNK -> 30L
        TitleGenres.GIRLSLOVE -> 31L
        TitleGenres.HAREM -> 32L
        TitleGenres.JOSEI -> 33L
        TitleGenres.ISEKAI -> 34L
    }
}
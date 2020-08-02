package pe.devpicon.android.marvelheroes.presentation.home

import android.text.SpannableString

data class SearchViewState(
        val id: Long,
        val name: String
)

data class HeroViewState(
        val thumbnailUrl: String,
        val name: SpannableString,
        val description: SpannableString,
        val comicViewStateList: MutableList<ComicViewState>
)

data class ComicViewState(
        val thumbnailUrl: String,
        val name: SpannableString,
        val description: SpannableString
)
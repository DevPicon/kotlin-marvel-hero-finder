package pe.devpicon.android.marvelheroes.presentation.home

data class SearchViewState(
        val id: Long,
        val name: String
)

data class HeroViewState(
        val thumbnailUrl: String,
        val name: String,
        val description: String,
        val comicViewStateList: List<ComicViewState>
)

data class ComicViewState(
        val thumbnailUrl: String,
        val name: String,
        val description: String
)
package pe.devpicon.android.marvelheroes.presentation.home

import pe.devpicon.android.marvelheroes.domain.Comic
import pe.devpicon.android.marvelheroes.domain.Hero

class MainViewStateMapper {

    fun mapHeroToSearchViewState(heroList: List<Hero>): List<SearchViewState> {
        return heroList.map { SearchViewState(it.id, it.name) }
    }

    fun mapHeroToViewState(hero: Hero, comicList: List<Comic> = emptyList()): HeroViewState = with(hero) {
        HeroViewState(
                thumbnailUrl = thumbnailUrl,
                name = "Name: $name",
                description = "Description:\n$description",
                comicViewStateList = mapComicListToViewState(comicList)
        )
    }

    fun mapComicListToViewState(comicList: List<Comic>): List<ComicViewState> {
        return comicList.map { mapComicToViewState(it) }
    }

    private fun mapComicToViewState(comic: Comic): ComicViewState = with(comic) {
        ComicViewState(
                thumbnailUrl = thumbnailUrl,
                name = "Title: $title",
                description = "Description:\n$description"
        )
    }

    fun mapHeroListToViewState(heroList: List<Hero>) = heroList.map { mapHeroToViewState(it) }
}
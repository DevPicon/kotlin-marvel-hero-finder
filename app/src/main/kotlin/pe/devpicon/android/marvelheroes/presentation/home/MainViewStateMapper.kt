package pe.devpicon.android.marvelheroes.presentation.home

import pe.devpicon.android.marvelheroes.domain.Hero

class MainViewStateMapper {

    fun mapHeroToViewState(heroList: List<Hero>): List<SearchViewState> {
        return heroList.map { SearchViewState(it.id, it.name) }
    }
}
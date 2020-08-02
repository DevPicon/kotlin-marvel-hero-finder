package pe.devpicon.android.marvelheroes.presentation.home

import pe.devpicon.android.marvelheroes.domain.Hero

class MainViewStateMapper {

    fun mapHeroToViewState(heroList: List<Hero>): List<HeroViewState> {
        return heroList.map { HeroViewState(it.id, it.name) }
    }
}
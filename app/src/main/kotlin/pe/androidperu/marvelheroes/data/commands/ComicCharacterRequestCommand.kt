package pe.androidperu.marvelheroes.data.commands

import pe.androidperu.marvelheroes.domain.mappers.CharacterDataMapper
import pe.androidperu.marvelheroes.domain.model.Hero

/**
 * Created by Armando on 4/9/2016.
 */
class ComicCharacterRequestCommand(): Command<List<Hero>>{

    override fun execute(): List<Hero> {
        return CharacterDataMapper().convertFromDataModel(ComicCharacterRequest().execute())
    }
}
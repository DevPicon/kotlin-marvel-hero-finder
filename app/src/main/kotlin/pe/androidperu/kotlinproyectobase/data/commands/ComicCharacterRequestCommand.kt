package pe.androidperu.kotlinproyectobase.data.commands

import pe.androidperu.kotlinproyectobase.domain.mappers.CharacterDataMapper
import pe.androidperu.kotlinproyectobase.domain.model.Hero

/**
 * Created by Armando on 4/9/2016.
 */
class ComicCharacterRequestCommand(): Command<List<Hero>>{

    override fun execute(): List<Hero> {
        return CharacterDataMapper().convertFromDataModel(ComicCharacterRequest().execute())
    }
}
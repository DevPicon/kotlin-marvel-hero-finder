package pe.androidperu.kotlinproyectobase.domain.mappers

import pe.androidperu.kotlinproyectobase.data.CharacterResult
import pe.androidperu.kotlinproyectobase.data.ComicCharacter
import pe.androidperu.kotlinproyectobase.domain.model.Hero
import java.util.*

/**
 *
 * Contiene las funciones que permitirán el mapeo de datos de las clases de respuesta en objetos
 * del modelo
 *
 * Created by Armando on 4/9/2016.
 */
class CharacterDataMapper {

    /**
     * Valida el resultado de la consulta antes de efectuar el mapeo
     */
    fun convertFromDataModel(characterResult: CharacterResult): List<Hero> {
        if (characterResult.code.equals(200)) {
            return convertCharacterListToDomain(characterResult.data.results)
        } else {
            return ArrayList<Hero>()
        }
    }

    /** .map es una función que retorna una lista conteniendo los resultados de una función de
     * transformación de datos
     *
     * @see https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/map.html */
    private fun convertCharacterListToDomain(result: List<ComicCharacter>): List<Hero> {
        return result.map { convertCharacterItemToDomain(it) }
    }

    private fun convertCharacterItemToDomain(character: ComicCharacter): Hero {
        return Hero(character.name, character.description)
    }
}
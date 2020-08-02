package pe.devpicon.android.marvelheroes.domain

import pe.devpicon.android.marvelheroes.data.local.CharacterEntity
import pe.devpicon.android.marvelheroes.data.local.ComicEntity
import pe.devpicon.android.marvelheroes.data.remote.ComicCharacterResponse

class DomainMapper {

    fun mapCharacterResponseToDomain(characterResponse: ComicCharacterResponse): Hero {
        return Hero(
                id = characterResponse.id,
                name = characterResponse.name,
                description = characterResponse.description,
                thumbnailUrl = generateThumbnailUrl(characterResponse.thumbnailResponse.path, characterResponse.thumbnailResponse.extension))
    }

    private fun generateThumbnailUrl(path: String, extension: String): String = "$path.$extension"

    fun mapCharacterEntityListToDomain(characterEntityList: List<CharacterEntity>): List<Hero> {
        return characterEntityList.map { mapCharacterEntityToDomain(it) }
    }

    private fun mapCharacterEntityToDomain(characterEntity: CharacterEntity): Hero = with(characterEntity) {
        Hero(
                id, name, description, thumbnailUrl
        )
    }

    fun mapComicEntityListToDomain(comicEntityList: List<ComicEntity>): List<Comic> {
        return comicEntityList.map { mapComicEntityToDomain(it) }
    }

    private fun mapComicEntityToDomain(comicEntity: ComicEntity): Comic = with(comicEntity) {
        Comic(
                id, title, description, thumbnailUrl
        )
    }

}
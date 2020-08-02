package pe.devpicon.android.marvelheroes.domain

import pe.devpicon.android.marvelheroes.data.remote.ComicCharacterResponse

class DomainMapper {

    fun mapCharacterItemToDomain(characterResponse: ComicCharacterResponse): Hero {
        return Hero(
                id = characterResponse.id,
                name = characterResponse.name,
                description = characterResponse.description,
                thumbnailUrl = generateThumbnailUrl(characterResponse.thumbnailResponse.path, characterResponse.thumbnailResponse.extension))
    }

    private fun generateThumbnailUrl(path: String, extension: String): String = "$path.$extension"

}
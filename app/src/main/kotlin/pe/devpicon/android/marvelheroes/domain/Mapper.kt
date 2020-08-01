package pe.devpicon.android.marvelheroes.domain

import pe.devpicon.android.marvelheroes.data.remote.ComicCharacterResponse

class Mapper {

    fun mapCharacterItemToDomain(characterResponse: ComicCharacterResponse): Hero {
        return Hero(characterResponse.name, characterResponse.description,
                generateThumbnailUrl(characterResponse.thumbnailResponse.path, characterResponse.thumbnailResponse.extension))
    }

    private fun generateThumbnailUrl(path: String, extension: String): String = "$path.$extension"

}
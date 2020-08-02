package pe.devpicon.android.marvelheroes.data.local

import pe.devpicon.android.marvelheroes.data.remote.ComicCharacterResponse
import pe.devpicon.android.marvelheroes.data.remote.ComicResponse

class DatabaseMapper {
    fun mapCharacterResponseToEntity(characterResponse: ComicCharacterResponse): CharacterEntity {
        return CharacterEntity(
                id = characterResponse.id,
                name = characterResponse.name,
                description = characterResponse.description,
                thumbnailUrl = with(characterResponse.thumbnailResponse) {
                    "${path}.${extension}"
                }
        )
    }

    fun mapComicResponseListToEntity(characterId: Long, comicResponseList: List<ComicResponse>): List<ComicEntity> {
        return comicResponseList.map { mapComicResponseToEntity(characterId, it) }
    }

    private fun mapComicResponseToEntity(characterId: Long, comicResponse: ComicResponse): ComicEntity {
        return ComicEntity(
                id = comicResponse.id,
                title = comicResponse.title,
                description = comicResponse.description,
                thumbnailUrl = with(comicResponse.thumbnailResponse) {
                    "${path}.${extension}"
                },
                characterId = characterId
        )
    }
}
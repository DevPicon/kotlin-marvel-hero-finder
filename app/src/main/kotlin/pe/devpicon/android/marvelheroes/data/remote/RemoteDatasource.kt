package pe.devpicon.android.marvelheroes.data.remote

class RemoteDatasource(private val service: MarvelService) {

    suspend fun searchHero(queryText: String): List<ComicCharacterResponse> {
        val result = service.fetchCharacters(queryText)
        return result.dataResponse.results
    }

    suspend fun fetchCharacter(characterId: Long): ComicCharacterResponse {
        val result = service.fetchCharacter(characterId)
        return result.dataResponse.results[0]
    }

    suspend fun fetchComics(characterId: Long): List<ComicResponse> {
        val result = service.fetchComics(characterId)
        return result.data.results
    }

}
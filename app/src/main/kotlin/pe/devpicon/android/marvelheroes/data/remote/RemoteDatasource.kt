package pe.devpicon.android.marvelheroes.data.remote

class RemoteDatasource(private val service: MarvelService) {

    suspend fun searchHero(queryText: String): List<ComicCharacterResponse> {
        val result = service.fetchCharacters(queryText)
        return result.dataResponse.results
    }

}
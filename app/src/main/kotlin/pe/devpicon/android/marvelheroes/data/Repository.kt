package pe.devpicon.android.marvelheroes.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import pe.devpicon.android.marvelheroes.data.local.DatabaseMapper
import pe.devpicon.android.marvelheroes.data.local.LocalDataSource
import pe.devpicon.android.marvelheroes.data.remote.ComicResponse
import pe.devpicon.android.marvelheroes.data.remote.RemoteDatasource
import pe.devpicon.android.marvelheroes.domain.DomainMapper
import pe.devpicon.android.marvelheroes.domain.Hero

@ExperimentalCoroutinesApi
class Repository(
        private val localDataSource: LocalDataSource,
        private val remoteDatasource: RemoteDatasource,
        private val databaseMapper: DatabaseMapper,
        private val domainMapper: DomainMapper
) {

    suspend fun searchHero(queryText: String): List<Hero> = try {
        remoteDatasource.searchHero(queryText)
                .map(domainMapper::mapCharacterResponseToDomain)
    } catch (e: Exception) {
        emptyList()
    }

    suspend fun fetchCharacterDetails(characterId: Long) = withContext(Dispatchers.IO) {

        // Fetch character and comic list
        val characterResponse =
                try {
                    remoteDatasource.fetchCharacter(characterId)
                } catch (e: Throwable) {
                    null
                }
        val comicResponseList = try {
            remoteDatasource.fetchComics(characterId)
        } catch (e: Throwable) {
            emptyList<ComicResponse>()
        }

        if (characterResponse != null) {
            localDataSource.updateCharacter(databaseMapper.mapCharacterResponseToEntity(characterResponse))
            localDataSource.updateComics(characterResponse.id, databaseMapper.mapComicResponseListToEntity(characterResponse.id, comicResponseList))
        }

    }

    fun getCharacters() = localDataSource.getCharacters()
            .map { domainMapper.mapCharacterEntityListToDomain(it) }

    fun getComics() = localDataSource.getAllComics()
            .map { domainMapper.mapComicEntityListToDomain(it) }

}
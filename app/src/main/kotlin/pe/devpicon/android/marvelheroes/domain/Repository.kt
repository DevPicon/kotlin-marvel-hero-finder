package pe.devpicon.android.marvelheroes.domain

import pe.devpicon.android.marvelheroes.data.remote.RemoteDatasource

class Repository(private val remoteDatasource: RemoteDatasource,
                 private val domainMapper: DomainMapper) {

    suspend fun searchHero(queryText: String): List<Hero> = try {
        remoteDatasource.searchHero(queryText)
                .map(domainMapper::mapCharacterItemToDomain)
    } catch (e: Exception) {
        emptyList()
    }

}
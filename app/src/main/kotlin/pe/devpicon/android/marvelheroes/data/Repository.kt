package pe.devpicon.android.marvelheroes.data

import pe.devpicon.android.marvelheroes.data.remote.RemoteDatasource
import pe.devpicon.android.marvelheroes.domain.DomainMapper
import pe.devpicon.android.marvelheroes.domain.Hero

class Repository(private val remoteDatasource: RemoteDatasource,
                 private val domainMapper: DomainMapper) {

    suspend fun searchHero(queryText: String): List<Hero> = try {
        remoteDatasource.searchHero(queryText)
                .map(domainMapper::mapCharacterItemToDomain)
    } catch (e: Exception) {
        emptyList()
    }

}
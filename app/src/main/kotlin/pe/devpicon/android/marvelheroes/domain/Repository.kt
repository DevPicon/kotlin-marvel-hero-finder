package pe.devpicon.android.marvelheroes.domain

import pe.devpicon.android.marvelheroes.data.remote.RemoteDatasource

class Repository(private val remoteDatasource: RemoteDatasource,
                 private val mapper: Mapper) {

    suspend fun searchHero(queryText: String): List<Hero> = try {
        remoteDatasource.searchHero(queryText)
                .map(mapper::mapCharacterItemToDomain)
    } catch (e: Exception) {
        ArrayList()
    }

}
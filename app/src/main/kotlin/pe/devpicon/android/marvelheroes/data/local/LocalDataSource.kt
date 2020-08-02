package pe.devpicon.android.marvelheroes.data.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(
        private val comicDao: ComicDao,
        private val characterDao: CharacterDao
) {

    fun getCharacters(): Flow<List<CharacterEntity>> {
        return characterDao.getAllCharacter()
    }

    fun getComicsByCharacter(characterId: Long): Flow<List<ComicEntity>> {
        return comicDao.getComicsByCharacter(characterId)
    }

    suspend fun updateCharacter(characterEntity: CharacterEntity) = characterDao.updateCharacter(characterEntity)

    suspend fun updateComics(characterId: Long, comicEntityList: List<ComicEntity>) {
        comicDao.updateComicsByCharacter(characterId, comicEntityList)
    }
}
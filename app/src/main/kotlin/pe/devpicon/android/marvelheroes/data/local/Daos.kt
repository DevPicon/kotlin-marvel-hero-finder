package pe.devpicon.android.marvelheroes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("select * from character")
    fun getAllCharacter(): Flow<List<CharacterEntity>>

    @Insert
    suspend fun insertCharacter(character: CharacterEntity)

    @Query("delete from character")
    fun clearAllCharacters()

    @Transaction
    suspend fun updateCharacter(characterEntity: CharacterEntity) {
        clearAllCharacters()
        insertCharacter(characterEntity)
    }

    // Unused operations

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}

@Dao
interface ComicDao {
    @Query("select * from comic")
    fun getAllComic(): Flow<List<ComicEntity>>

    @Insert
    suspend fun insertComics(comicList: List<ComicEntity>)

    @Query("delete from comic")
    suspend fun clearAllComics()

    @Transaction
    suspend fun updateComicsByCharacter(characterId: Long, comicList: List<ComicEntity>) {
        clearAllComics()
        insertComics(comicList)
    }

    // Unused operations

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comic: ComicEntity)

    @Query("select * from comic where characterId = :characterId")
    fun getComicsByCharacter(characterId: Long): Flow<List<ComicEntity>>

    @Query("delete from comic where characterId = :characterId")
    suspend fun deleteAllComicsByCharacter(characterId: Long)
}
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

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)

    @Transaction
    suspend fun updateCharacter(characterEntity: CharacterEntity) {
        deleteCharacter(characterEntity)
        insertCharacter(characterEntity)
    }
}

@Dao
interface ComicDao {
    @Query("select * from comic")
    fun getAllComic(): Flow<List<ComicEntity>>

    @Query("select * from comic where characterId = :characterId")
    fun getComicsByCharacter(characterId: Long): Flow<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comic: ComicEntity)

    @Insert
    suspend fun insertComics(comicList: List<ComicEntity>)

    @Transaction
    suspend fun updateComicsByCharacter(characterId: Long, comicList: List<ComicEntity>) {
        deleteAllComicsByCharacter(characterId)
        insertComics(comicList)
    }


    @Query("delete from comic where characterId = :characterId")
    suspend fun deleteAllComicsByCharacter(characterId: Long)
}
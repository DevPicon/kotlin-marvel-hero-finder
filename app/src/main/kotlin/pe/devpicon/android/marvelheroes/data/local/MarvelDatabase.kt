package pe.devpicon.android.marvelheroes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "marvel_database"

@Database(entities = [CharacterEntity::class, ComicEntity::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
    abstract fun characterDao(): CharacterDao

    companion object {
        fun create(context: Context): MarvelDatabase {
            return Room.databaseBuilder(
                    context,
                    MarvelDatabase::class.java,
                    DATABASE_NAME
            ).build()
        }
    }
}
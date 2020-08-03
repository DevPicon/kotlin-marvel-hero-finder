package pe.devpicon.android.marvelheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
        @PrimaryKey val id: Long,
        val name: String,
        val description: String,
        val thumbnailUrl: String
)

@Entity(tableName = "comic")
data class ComicEntity(
        @PrimaryKey val id: Long,
        val title: String,
        val description: String?,
        val thumbnailUrl: String,
        val characterId: Long
)
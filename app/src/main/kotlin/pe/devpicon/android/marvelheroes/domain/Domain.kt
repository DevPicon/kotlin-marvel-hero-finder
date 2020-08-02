package pe.devpicon.android.marvelheroes.domain

data class Hero(
        val id: Long,
        val name: String,
        val description: String,
        val thumbnailUrl: String)


data class Comic(
        val id: Long,
        val title: String,
        val description: String,
        val thumbnailUrl: String
)
package pe.devpicon.android.marvelheroes.data.networking


data class CharacterResult(val code: Int, val status: String, val copyright: String,
                           val attributionText: String, val data: CharacterDataContainer)

data class CharacterDataContainer(val offset: Int, val limit: Int, val total: Int, val count: Int,
                                  val results: List<ComicCharacter>)

data class ComicCharacter(val id: Long, val name: String, val description: String, val modified: String,
                          val resourceUri: String, val thumbnail: Thumbnail)

data class Thumbnail(val path: String, val extension: String)
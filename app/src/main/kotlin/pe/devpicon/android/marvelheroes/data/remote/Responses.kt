package pe.devpicon.android.marvelheroes.data.remote


data class CharacterResultResponse(val code: Int, val status: String, val copyright: String,
                                   val attributionText: String, val dataResponse: CharacterDataContainerResponse)

data class CharacterDataContainerResponse(val offset: Int, val limit: Int, val total: Int, val count: Int,
                                          val results: List<ComicCharacterResponse>)

data class ComicCharacterResponse(val id: Long, val name: String, val description: String, val modified: String,
                                  val resourceUri: String, val thumbnailResponse: ThumbnailResponse)

data class ThumbnailResponse(val path: String, val extension: String)
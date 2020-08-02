package pe.devpicon.android.marvelheroes.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun fetchCharacters(
            @Query("nameStartsWith") nameStartWith: String
    ): CharacterResultResponse

    @GET("v1/public/characters/{characterId}")
    suspend fun fetchCharacter(
            @Path("characterId") characterId: Long
    ): CharacterResultResponse

    @GET("v1/public/comics")
    suspend fun fetchComics(
            @Query("characters") characterId: Long
    ): ComicDataWrapperResponse

}
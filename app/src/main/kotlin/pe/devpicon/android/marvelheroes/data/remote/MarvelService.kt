package pe.devpicon.android.marvelheroes.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun fetchCharacters(
            @Query("nameStartsWith") nameStartWith: String
    ): CharacterResultResponse

}
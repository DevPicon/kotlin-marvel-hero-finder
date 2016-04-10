package pe.androidperu.marvelheroes.data.commands

import android.util.Log
import com.google.gson.Gson
import pe.androidperu.marvelheroes.data.CharacterResult
import java.net.URL

/**
 * Created by Armando on 4/9/2016.
 * Esta clase se encargará inicialmente de efectuar la llamada al servicio.
 */
class ComicCharacterRequest {

    /**
     * El valor de estas constantes deben cambiarse por las propias
     * @see https://developer.marvel.com/documentation/authorization
     * @see https://kotlinlang.org/docs/reference/object-declarations.html#companion-objects
     */
    companion object {
        private val PUBLIC_API_KEY = "a953888a4098cff50b054c9375839786"
        private val RANDOM_WORD = "armando"
        private val HASH = "7f3db60eab45a2c204d69b8aafdcfbc9"
        private val URL = "http://gateway.marvel.com:80/v1/public/characters"
        private val COMPLETE_URL = "$URL?ts=$RANDOM_WORD&apikey=$PUBLIC_API_KEY&hash=$HASH"

    }

    /**
     * @see https://kotlinlang.org/docs/reference/java-interop.html#getclass
     */
    fun execute(): CharacterResult {
        val comicCharacterJsonStr = URL(COMPLETE_URL).readText()
        val result = Gson().fromJson(comicCharacterJsonStr, CharacterResult::class.java)
        Log.d(javaClass.simpleName, result.toString())
        return result
    }
}
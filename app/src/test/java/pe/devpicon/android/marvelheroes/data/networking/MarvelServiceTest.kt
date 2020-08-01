package pe.devpicon.android.marvelheroes.data.networking

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pe.devpicon.android.marvelheroes.data.networking.RestClient.service

class MarvelServiceTest {

    @Test
    fun `When Sp text is inserted Then response contains Spiderman`() {
        runBlocking {
            val fetchCharacters = service.fetchCharacters("sp")
            Assert.assertTrue(fetchCharacters.data.results.isNotEmpty())
        }

    }
}
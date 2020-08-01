package pe.androidperu.marvelheroes.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import pe.androidperu.marvelheroes.data.commands.ComicCharacterRequestCommand
import pe.androidperu.marvelheroes.databinding.ActivityMainBinding
import pe.devpicon.android.marvelheroes.domain.Hero
import pe.androidperu.marvelheroes.ui.adapters.ComicCharacterListAdapter

/**
 * El activity principal
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Aqui estamos usando la función 'find' de Anko para obtener el recycler view
        binding.characterListRecyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * Async nos permite remplazar el AsynTask y mediante el uiThread podemos acceder al hilo
         * de la UI para efectuar alguna operación
         */
        async() {
            val result = ComicCharacterRequestCommand().execute()
            uiThread {
                binding.characterListRecyclerView.adapter = ComicCharacterListAdapter(result) { comicCharacter: Hero ->
                    toast(comicCharacter.description)
                }
                longToast("Se realizó la consulta")
            }
        }

    }
}

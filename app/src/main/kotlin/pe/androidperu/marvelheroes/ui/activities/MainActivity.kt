package pe.androidperu.marvelheroes.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import pe.androidperu.marvelheroes.R
import pe.androidperu.marvelheroes.data.commands.ComicCharacterRequestCommand
import pe.androidperu.marvelheroes.domain.model.Hero
import pe.androidperu.marvelheroes.ui.adapters.ComicCharacterListAdapter

/**
 * El activity principal
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Aqui estamos usando la función 'find' de Anko para obtener el recycler view
        characterListRecyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * Async nos permite remplazar el AsynTask y mediante el uiThread podemos acceder al hilo
         * de la UI para efectuar alguna operación
         */
        async() {
            val result = ComicCharacterRequestCommand().execute()
            uiThread {
                characterListRecyclerView.adapter = ComicCharacterListAdapter(result) {
                    comicCharacter: Hero ->
                    toast(comicCharacter.description)
                }
                longToast("Se realizó la consulta")
            }
        }

    }
}

package pe.devpicon.android.marvelheroes.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import pe.androidperu.marvelheroes.data.commands.ComicCharacterRequestCommand
import pe.androidperu.marvelheroes.databinding.ActivityMainBinding
import pe.devpicon.android.marvelheroes.app.MarvelHeroesApp
import pe.devpicon.android.marvelheroes.domain.Hero
import pe.devpicon.android.marvelheroes.presentation.MainViewModelFactory

/**
 * El activity principal
 */
@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val searchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appContainer = (application as MarvelHeroesApp).appContainer
        mainViewModel = MainViewModelFactory(appContainer.viewStateMapper, appContainer.repository).create()

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

        initSearchBar()
        initObservers()
    }

    private fun initObservers() {
        mainViewModel.heroes.observe(this, Observer {
            searchAdapter.setData(it)
        })
    }

    private fun initSearchBar() {
        with(binding) {
            lvSearchResultList.adapter = searchAdapter

            svSearchHero.isActivated = true
            svSearchHero.onActionViewExpanded()
            svSearchHero.isIconified
            svSearchHero.clearFocus()

            svSearchHero.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    mainViewModel.queryChannel.offer(newText)
                    return false
                }
            })
        }
    }
}

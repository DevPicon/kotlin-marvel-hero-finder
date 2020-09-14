package pe.devpicon.android.marvelheroes.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import pe.androidperu.marvelheroes.R
import pe.androidperu.marvelheroes.databinding.ActivityMainBinding
import pe.devpicon.android.marvelheroes.app.MarvelHeroesApp
import pe.devpicon.android.marvelheroes.presentation.MainViewModelFactory
import pe.devpicon.android.marvelheroes.presentation.home.MainScreenState.LOADING_DATA
import pe.devpicon.android.marvelheroes.presentation.home.MainScreenState.SHOW_DATA

/**
 * El activity principal
 */
@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val searchAdapter by lazy {
        SearchAdapter(::onSelectResultHero)
    }
    private val comicListAdapter by lazy {
        ComicListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appContainer = (application as MarvelHeroesApp).appContainer
        mainViewModel = MainViewModelFactory(appContainer.viewStateMapper, appContainer.repository).create()

        initUI()
        initObservers()
        initTransitionListeners()
    }

    private fun initTransitionListeners() {
        binding.clContent.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, start: Int, end: Int, p3: Float) {
                println("Veamos $start $end $p3")

            }

            override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                if (currentId == R.id.end) {
                    mainViewModel.getComics()
                }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

        })
    }

    private fun initUI() {
        initRecyclerView()
        initSearchBar()
    }

    private fun initRecyclerView() {
        binding.characterListRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.characterListRecyclerView.adapter = comicListAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.characterListRecyclerView)
    }

    private fun initObservers() {
        mainViewModel.screenState.observe(this, Observer {
            when (it) {
                LOADING_DATA -> displayLoaderOnSearch()
                SHOW_DATA -> hideLoaderOnSearch()
                else -> hideLoaderOnSearch()
            }
        })

        mainViewModel.heroes.observe(this, Observer {
            searchAdapter.setData(it)
        })

        mainViewModel.character.observe(this, Observer {
            Toast.makeText(this, "hero ${it.size}", Toast.LENGTH_SHORT).show()
            if (it.isNotEmpty()) {
                displayHeroData(it[0])
            }
        })

        mainViewModel.comics.observe(this, Observer {
            comicListAdapter.updateData(it)
        })
    }

    private fun displayLoaderOnSearch() {
        binding.pbSearchLoader.visibility = View.VISIBLE
        binding.tvSearchLoader.visibility = View.VISIBLE
    }

    private fun hideLoaderOnSearch() {
        binding.pbSearchLoader.visibility = View.GONE
        binding.tvSearchLoader.visibility = View.GONE
    }

    private fun displayHeroData(heroViewState: HeroViewState) = with(heroViewState) {
        binding.tvHeroName.text = name
        binding.tvHeroDescription.text = description
        Picasso.get().load(thumbnailUrl)
                .error(R.drawable.broken_shield).into(binding.ivHeroThumbnail)
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

    private fun onSelectResultHero(searchViewState: SearchViewState) {
        mainViewModel.queryChannel.offer("")
        mainViewModel.fetchCharacterAndComic(searchViewState.id)
    }
}

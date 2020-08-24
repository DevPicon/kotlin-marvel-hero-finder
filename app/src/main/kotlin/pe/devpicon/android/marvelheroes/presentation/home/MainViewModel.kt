package pe.devpicon.android.marvelheroes.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import pe.devpicon.android.marvelheroes.data.Repository
import pe.devpicon.android.marvelheroes.presentation.home.MainScreenState.INITIAL
import pe.devpicon.android.marvelheroes.presentation.home.MainScreenState.LOADING_DATA
import pe.devpicon.android.marvelheroes.presentation.home.MainScreenState.SHOW_DATA

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(
        private val mainViewStateMapper: MainViewStateMapper,
        private val repository: Repository
) : ViewModel() {

    private val _screenState: MutableLiveData<MainScreenState> by lazy { MutableLiveData<MainScreenState>(initState()) }

    val screenState: LiveData<MainScreenState>
        get() = _screenState

    private fun initState(): MainScreenState = INITIAL

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    private val _heroes = queryChannel
            .asFlow()
            .debounce(SEARCH_DELAY_MILLIS)
            .mapLatest {
                if (it.length >= MIN_QUERY_LENGTH) {
                    searchHero(it)
                } else {
                    emptyList()
                }
            }
            .catch {
                println(this.toString())
            }

    val heroes = _heroes
            .asLiveData()

    private suspend fun searchHero(queryText: String): List<SearchViewState> {
        _screenState.value = LOADING_DATA
        val heroListResult = viewModelScope.async { repository.searchHero(queryText) }
        heroListResult.invokeOnCompletion { _screenState.value = SHOW_DATA }
        return mainViewStateMapper.mapHeroToSearchViewState(heroListResult.await())
    }

    // ---- Details and Comics

    fun fetchCharacterAndComic(characterId: Long) {
        viewModelScope.launch {
            repository.fetchCharacterDetails(characterId)
        }
    }

    val character: LiveData<List<HeroViewState>> = repository.getCharacters()
            .map {
                mainViewStateMapper.mapHeroListToViewState(it)
            }
            .asLiveData()

    val comics: LiveData<List<ComicViewState>> = repository.getComics()
            .map {
                mainViewStateMapper.mapComicListToViewState(it)
            }
            .asLiveData()

    companion object {
        const val SEARCH_DELAY_MILLIS = 1000L
        const val MIN_QUERY_LENGTH = 3
    }
}
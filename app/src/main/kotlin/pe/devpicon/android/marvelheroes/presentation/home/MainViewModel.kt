package pe.devpicon.android.marvelheroes.presentation.home

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
import kotlinx.coroutines.flow.mapLatest
import pe.devpicon.android.marvelheroes.data.Repository

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(
        private val mainViewStateMapper: MainViewStateMapper,
        private val repository: Repository
) : ViewModel() {

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

    val heroes = _heroes.asLiveData()

    suspend fun searchHero(queryText: String): List<SearchViewState> {
        val heroListResult = viewModelScope.async { repository.searchHero(queryText) }
        return mainViewStateMapper.mapHeroToViewState(heroListResult.await())
    }

    companion object {
        const val SEARCH_DELAY_MILLIS = 2000L
        const val MIN_QUERY_LENGTH = 3
    }
}
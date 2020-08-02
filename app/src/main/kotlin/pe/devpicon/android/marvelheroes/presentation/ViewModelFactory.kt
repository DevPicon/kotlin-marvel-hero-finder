package pe.devpicon.android.marvelheroes.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import pe.devpicon.android.marvelheroes.domain.Repository
import pe.devpicon.android.marvelheroes.presentation.home.MainViewModel
import pe.devpicon.android.marvelheroes.presentation.home.MainViewStateMapper


interface ViewModelFactory<out T> {
    fun create(): T
}

@ExperimentalCoroutinesApi
@FlowPreview
class MainViewModelFactory(private val mainViewModelMapper: MainViewStateMapper,
                           private val repository: Repository) : ViewModelFactory<MainViewModel> {
    override fun create(): MainViewModel {
        return MainViewModel(mainViewModelMapper, repository)
    }
}
package pe.devpicon.android.marvelheroes.presentation.home

import pe.devpicon.android.marvelheroes.presentation.ui.ScreenState

enum class MainScreenState : ScreenState {
    INITIAL, LOADING_DATA, SHOW_DATA;

    override fun getInitialState(): ScreenState = INITIAL
}
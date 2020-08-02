package pe.devpicon.android.marvelheroes.app

import android.app.Application

class MarvelHeroesApp : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)
    }
}



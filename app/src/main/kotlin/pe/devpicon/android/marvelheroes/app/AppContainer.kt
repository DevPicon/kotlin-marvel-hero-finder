package pe.devpicon.android.marvelheroes.app

import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pe.devpicon.android.marvelheroes.data.Repository
import pe.devpicon.android.marvelheroes.data.local.DatabaseMapper
import pe.devpicon.android.marvelheroes.data.local.LocalDataSource
import pe.devpicon.android.marvelheroes.data.local.MarvelDatabase
import pe.devpicon.android.marvelheroes.data.remote.MarvelService
import pe.devpicon.android.marvelheroes.data.remote.RemoteDatasource
import pe.devpicon.android.marvelheroes.data.remote.RestClient
import pe.devpicon.android.marvelheroes.domain.DomainMapper
import pe.devpicon.android.marvelheroes.presentation.home.MainViewStateMapper

@ExperimentalCoroutinesApi
class AppContainer(applicationContext: Context) {
    private val service: MarvelService = RestClient.service
    private val remoteDataSource = RemoteDatasource(service)
    private val domainMapper = DomainMapper()


    private val marvelDatabase = MarvelDatabase.create(applicationContext)
    private val comicDao = marvelDatabase.comicDao()
    private val characterDao = marvelDatabase.characterDao()
    private val localDataSource = LocalDataSource(comicDao, characterDao)
    private val databaseMapper = DatabaseMapper()

    val repository = Repository(localDataSource, remoteDataSource, databaseMapper, domainMapper)

    val viewStateMapper = MainViewStateMapper()
}
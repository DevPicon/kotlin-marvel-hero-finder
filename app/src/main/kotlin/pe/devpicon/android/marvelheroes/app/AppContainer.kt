package pe.devpicon.android.marvelheroes.app

import android.content.Context
import pe.devpicon.android.marvelheroes.data.remote.MarvelService
import pe.devpicon.android.marvelheroes.data.remote.RemoteDatasource
import pe.devpicon.android.marvelheroes.data.remote.RestClient
import pe.devpicon.android.marvelheroes.domain.DomainMapper
import pe.devpicon.android.marvelheroes.data.Repository
import pe.devpicon.android.marvelheroes.presentation.home.MainViewStateMapper

class AppContainer(applicationContext: Context) {
    private val service: MarvelService = RestClient.service
    private val remoteDataSource = RemoteDatasource(service)
    private val domainMapper = DomainMapper()

    val repository = Repository(remoteDataSource, domainMapper)

    val viewStateMapper = MainViewStateMapper()
}
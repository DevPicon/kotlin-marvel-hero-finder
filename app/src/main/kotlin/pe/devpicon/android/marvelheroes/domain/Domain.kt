package pe.devpicon.android.marvelheroes.domain


/**
 * Created by Armando on 4/9/2016.
 *
 * Este archivo contendrá las clases correspondientes al dominio
 */
data class Hero(
        val id: Long,
        val name: String,
        val description: String,
        val thumbnailUrl: String)

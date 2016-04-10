package pe.androidperu.marvelheroes.data.commands

/**
 * Created by Armando on 4/9/2016.
 */
interface Command<T>{
    fun execute(): T
}
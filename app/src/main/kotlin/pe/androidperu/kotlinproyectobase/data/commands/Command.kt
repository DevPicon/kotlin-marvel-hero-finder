package pe.androidperu.kotlinproyectobase.data.commands

/**
 * Created by Armando on 4/9/2016.
 */
interface Command<T>{
    fun execute(): T
}
package pe.androidperu.marvelheroes.ui.utils

import android.content.Context
import android.view.View

/**
 * Created by Armando on 4/10/2016.
 *
 * Mediante esta extensión agregamos una propiedad a la clase View para obtener el contexto en
 * cualquier objeto que derive de la clase [View]
 */
val View.ctx: Context
    get() = context
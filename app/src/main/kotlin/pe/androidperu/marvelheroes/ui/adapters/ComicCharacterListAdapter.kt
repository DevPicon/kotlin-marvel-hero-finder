package pe.androidperu.marvelheroes.ui.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hero.view.*
import pe.androidperu.marvelheroes.R
import pe.androidperu.marvelheroes.domain.model.Hero
import pe.androidperu.marvelheroes.ui.utils.ctx

/**
 * Created by Armando on 4/9/2016.
 *
 * Empleamos un import sintético para tener acceso directo a los elementos de la vista
 */
class ComicCharacterListAdapter(val items: List<Hero>, val itemClick: (Hero) -> Unit) :
        RecyclerView.Adapter<ComicCharacterListAdapter.ViewHolder>() {

    // Cuando una función cuenta con una sola línea, esta se puede simplificar
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_hero, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCharacter(items[position])
    }

    class ViewHolder(view: View, val itemClick: (Hero) -> Unit) : RecyclerView.ViewHolder(view) {


        fun bindCharacter(character: Hero) {
            with(character) {
                Log.d(javaClass.simpleName, thumbnailUrl)
                Picasso.with(itemView.ctx).load(thumbnailUrl)
                        .resize(48, 48)
                        .centerCrop()
                        .into(itemView.heroThumbnailImageView)
                itemView.heroNameTextView.text = name
                itemView.heroOverviewTextView.text = description
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    /*interface OnItemClickListener {
        operator fun invoke(comicCharacter: Hero)
    }*/

}
package pe.androidperu.marvelheroes.ui.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import pe.androidperu.marvelheroes.R
import pe.androidperu.marvelheroes.domain.model.Hero
import pe.androidperu.marvelheroes.ui.utils.ctx

/**
 * Created by Armando on 4/9/2016.
 */
class ComicCharacterListAdapter(val items: List<Hero>) :
        RecyclerView.Adapter<ComicCharacterListAdapter.ViewHolder>() {

    // Cuando una función cuenta con una sola línea, esta se puede simplificar
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_hero, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCharacter(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val thumbnailView: ImageView
        private val nameView: TextView
        private val descriptionView: TextView

        init {
            thumbnailView = view.find(R.id.heroThumbnailImageView)
            nameView = view.find(R.id.heroNameTextView)
            descriptionView = view.find(R.id.heroOverviewTextView)
        }

        fun bindCharacter(character: Hero) {
            with(character) {
                Log.d(javaClass.simpleName, thumbnailUrl)
                Picasso.with(itemView.ctx).load(thumbnailUrl)
                        .resize(48, 48)
                        .centerCrop()
                        .into(thumbnailView)
                nameView.text = name
                descriptionView.text = description
            }
        }
    }

}
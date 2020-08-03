package pe.devpicon.android.marvelheroes.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.androidperu.marvelheroes.R
import pe.androidperu.marvelheroes.databinding.ComicListItemBinding

class ComicListAdapter() : RecyclerView.Adapter<ComicViewHolder>() {

    private val comicViewStateList = mutableListOf<ComicViewState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ComicListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun getItemCount(): Int = comicViewStateList.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comicViewStateList[position])
    }

    fun updateData(comicViewList: List<ComicViewState>) {
        this.comicViewStateList.clear()
        this.comicViewStateList.addAll(comicViewList)
        notifyDataSetChanged()
    }
}

class ComicViewHolder(val binding: ComicListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(comicViewState: ComicViewState) {
        binding.tvComicTitle.text = comicViewState.name
        binding.tvComicDescription.text = comicViewState.description
        Picasso.get().load(comicViewState.thumbnailUrl)
                .error(R.drawable.broken_shield)
                .into(binding.ivComicThumbnail)
    }
}
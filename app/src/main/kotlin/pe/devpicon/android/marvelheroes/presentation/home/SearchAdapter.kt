package pe.devpicon.android.marvelheroes.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import pe.androidperu.marvelheroes.databinding.HeroSearchListItemBinding

class SearchAdapter : BaseAdapter() {

    private val heroes = mutableListOf<HeroViewState>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemBinding = HeroSearchListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemBinding.tvSearchResultHero.text = heroes[position].name
        return itemBinding.root
    }

    override fun getItem(position: Int) = heroes[position]

    override fun getItemId(position: Int): Long = heroes[position].id

    override fun getCount(): Int = heroes.size

    fun setData(heroResultList: List<HeroViewState>) {
        this.heroes.clear()
        this.heroes.addAll(heroResultList)
        notifyDataSetChanged()
    }
}
package pe.devpicon.android.marvelheroes.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import pe.androidperu.marvelheroes.databinding.HeroSearchListItemBinding

class SearchAdapter(val onSelectResultHero: (searchViewState: SearchViewState) -> Unit) : BaseAdapter() {

    private val searchResultViewStateList = mutableListOf<SearchViewState>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemBinding = HeroSearchListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemBinding.tvSearchResultHero.text = searchResultViewStateList[position].name
        itemBinding.tvSearchResultHero.setOnClickListener { onSelectResultHero(searchResultViewStateList[position]) }
        return itemBinding.root
    }

    override fun getItem(position: Int) = searchResultViewStateList[position]

    override fun getItemId(position: Int): Long = searchResultViewStateList[position].id

    override fun getCount(): Int = searchResultViewStateList.size

    fun setData(searchResultList: List<SearchViewState>) {
        this.searchResultViewStateList.clear()
        this.searchResultViewStateList.addAll(searchResultList)
        notifyDataSetChanged()
    }
}
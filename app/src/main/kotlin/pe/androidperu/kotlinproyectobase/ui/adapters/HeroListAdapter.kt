package pe.androidperu.kotlinproyectobase.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Armando on 4/9/2016.
 */
class HeroListAdapter(val items: List<String>) : RecyclerView.Adapter<HeroListAdapter.ViewHolder>() {

    // Cuando una función cuenta con una sola línea, esta se puede simplificar
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(TextView(parent.context))
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}
package pe.androidperu.kotlinproyectobase.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.find
import pe.androidperu.kotlinproyectobase.R
import pe.androidperu.kotlinproyectobase.ui.adapters.HeroListAdapter

class MainActivity : AppCompatActivity() {

    private val heroes = listOf<String>("Ironman", "Captain America", "Spider-man", "Deadpool", "Wolverine")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Aqui estamos usando la función 'find' de Anko para obtener el recycler view
        val heroesList: RecyclerView = find(R.id.heroList)
        heroesList.layoutManager = LinearLayoutManager(this)
        heroesList.adapter = HeroListAdapter(heroes)

    }
}

package com.example.wpam_wastesorting

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wpam_wastesorting.network.TrashEntry
import kotlinx.android.synthetic.main.trash_grid_fragment.*

class TrashGridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
        setContentView(R.layout.trash_grid_fragment)
        onCreateView()
//        onCreateOptionsMenu()

    }

     fun onCreateView( ){
        // Inflate the layout for this fragment with the ProductGrid theme
//        val view = inflater.inflate(R.layout.trash_grid_fragment, container, false)

        // Set up the toolbar.
//        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        // Set up the RecyclerView
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(this@TrashGridActivity, 2, RecyclerView.VERTICAL, false)
        val adapter = TrashCardRecyclerViewAdapter(
            this,
                TrashEntry.initTrashEntryList(resources))
        recycler_view.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.product_grid_spacing_small)
        recycler_view.addItemDecoration(TrashGridItemDecoration(largePadding, smallPadding))

    }

//     fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
//        menuInflater.inflate(R.menu.toolbar_menu, menu)
//        super.onCreateOptionsMenu(menu, menuInflater)
//    }
}
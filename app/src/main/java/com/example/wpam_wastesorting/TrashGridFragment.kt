package com.example.wpam_wastesorting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.trash_grid_fragment.*
import kotlinx.android.synthetic.main.trash_grid_fragment.view.*

class TrashGridFragment : Fragment() {
    private  lateinit var trashListAdapter: TrashCardRecyclerViewAdapter




     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Set up the RecyclerView
         val view = inflater.inflate(R.layout.trash_grid_fragment, container, false)
         view.loading_spinner_grid.bringToFront()
         view.loading_spinner_grid.isVisible = true
         ImageService.getImages(){success, imagesList ->
             trashListAdapter.update(imagesList)
             view.loading_spinner_grid.isVisible = false
         }
         return view;

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        addData()
    }
    private fun addData(){
        val data = ArrayList<ImageModel>()
        trashListAdapter.submitList(data)

    }
    private fun initRecyclerView(){
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        trashListAdapter = TrashCardRecyclerViewAdapter()
        recycler_view.adapter = trashListAdapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.product_grid_spacing_small)
        recycler_view.addItemDecoration(TrashGridItemDecoration(largePadding, smallPadding))
    }

}
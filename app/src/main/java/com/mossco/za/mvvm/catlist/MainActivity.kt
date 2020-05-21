package com.mossco.za.mvvm.catlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mossco.za.mvvm.CatDetailsActivity
import com.mossco.za.mvvm.R
import com.mossco.za.mvvm.catlist.CatListAdapter.ItemOnclickListener
import com.mossco.za.mvvm.catlist.model.CatDto
import com.mossco.za.mvvm.database.CatEntry
import com.mossco.za.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemOnclickListener {
    private var catListAdapter: CatListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        catListAdapter = CatListAdapter(ItemOnclickListener { catEntry: CatEntry -> onItemClicked(catEntry) })
        binding.catListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.catListRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.catListRecyclerView.adapter = catListAdapter
        setUpViewModelData()
    }

    override fun onItemClicked(catEntry: CatEntry) {
        val intent = Intent(this, CatDetailsActivity::class.java)
        intent.putExtra("cat_entry", getMappedCatDtoFromDb(catEntry))
        startActivity(intent)
    }

    private fun setUpViewModelData() {
        val viewModel = ViewModelProviders.of(this).get(CatListViewModel::class.java)
        viewModel.catListLiveData.observe(this, Observer { catEntries: List<CatEntry?>? ->
            //update ui with data from db
            catListAdapter?.setCatListItems(catEntries)
        })
    }

    private fun getMappedCatDtoFromDb(catEntry: CatEntry): CatDto {
        val catDto = CatDto()
        catDto.description = catEntry.description
        catDto.imageId = catEntry.imageId
        catDto.imageUrl = catEntry.imageUrl
        catDto.title = catEntry.title
        return catDto
    }
}
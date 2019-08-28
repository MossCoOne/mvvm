package com.mossco.za.mvvm.catlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mossco.za.mvvm.CatDetailsActivity;
import com.mossco.za.mvvm.R;
import com.mossco.za.mvvm.catlist.model.CatDto;
import com.mossco.za.mvvm.database.CatEntry;
import com.mossco.za.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements CatListAdapter.ItemOnclickListener {

    private CatListAdapter catListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        catListAdapter = new CatListAdapter(this::onItemClicked);
        binding.catListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.catListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.catListRecyclerView.setAdapter(catListAdapter);
        setUpViewModelData();
    }

    @Override
    public void onItemClicked(CatEntry catEntry) {
        Intent intent = new Intent(this, CatDetailsActivity.class);
        intent.putExtra("cat_entry", getMappedCatDtoFromDb(catEntry));
        startActivity(intent);
    }

    private void setUpViewModelData() {
        CatListViewModel viewModel = ViewModelProviders.of(this).get(CatListViewModel.class);

        viewModel.getCatListLiveData().observe(this, catEntries -> {
            //update ui with data from db
            catListAdapter.setCatListItems(catEntries);
        });
    }

    public CatDto getMappedCatDtoFromDb(CatEntry catEntry){
        CatDto  catDto =  new CatDto();
        catDto.setDescription(catEntry.getDescription());
        catDto.setImageId(catEntry.getImageId());
        catDto.setImageUrl(catEntry.getImageUrl());
        catDto.setTitle(catEntry.getTitle());
        return catDto;
    }
}

package com.mossco.za.mvvm.catlist.model;

import androidx.lifecycle.LiveData;
import com.mossco.za.mvvm.database.CatEntry;

import java.util.List;

public interface CatRepository {
    void loadCatListFromServer();

    LiveData<List<CatEntry>> readCatListFromDb();

}

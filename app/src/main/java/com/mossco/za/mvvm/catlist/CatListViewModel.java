package com.mossco.za.mvvm.catlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mossco.za.mvvm.catlist.model.CatRepository;
import com.mossco.za.mvvm.catlist.model.CatRepositoryImplementation;
import com.mossco.za.mvvm.database.AppDatabase;
import com.mossco.za.mvvm.database.CatEntry;

import java.util.List;

public class CatListViewModel extends AndroidViewModel {
    private LiveData<List<CatEntry>> catListLiveData;
    private CatRepository catRepository;

    public CatListViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        catRepository = new CatRepositoryImplementation(appDatabase);
        catListLiveData = catRepository.readCatListFromDb();
        if (catListLiveData.getValue() == null) {
            catRepository.loadCatListFromServer();
        }
    }

    public LiveData<List<CatEntry>> getCatListLiveData() {
        return catListLiveData;
    }
}

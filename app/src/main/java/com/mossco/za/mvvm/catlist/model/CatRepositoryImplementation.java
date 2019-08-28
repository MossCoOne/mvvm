package com.mossco.za.mvvm.catlist.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import com.mossco.za.mvvm.database.AppDatabase;
import com.mossco.za.mvvm.database.CatEntry;
import com.mossco.za.mvvm.serviceapi.CatServiceApi;
import com.mossco.za.mvvm.serviceapi.CatServiceApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CatRepositoryImplementation implements CatRepository {
    CatServiceApi catServiceApi;
    AppDatabase appDatabase;

    public CatRepositoryImplementation(AppDatabase appDatabase) {
        catServiceApi = CatServiceApiClient.getInstance();
        this.appDatabase = appDatabase;
    }

    @Override
    public void loadCatListFromServer() {
        catServiceApi.getListOfCats().enqueue(new Callback<List<CatListItem>>() {
            @Override
            public void onResponse(Call<List<CatListItem>> call, Response<List<CatListItem>> response) {
                final List<CatListItem> body = response.body();
                if (response.isSuccessful() && body != null) {
                    if (appDatabase.catDao().retrieveListOfAllCats().getValue() == null) {
                        Executor executor = Executors.newSingleThreadExecutor();
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                appDatabase.catDao().insertAllCatEntries(mapListOfCatEntriesFromResponse(body));
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CatListItem>> call, Throwable t) {
                Log.d("Failed", t.getMessage());
            }
        });
    }

    @Override
    public LiveData<List<CatEntry>> readCatListFromDb() {
        return appDatabase.catDao().retrieveListOfAllCats();
    }

    private List<CatEntry> mapListOfCatEntriesFromResponse(List<CatListItem> catListItems) {
        List<CatEntry> catEntryList = new ArrayList<>();
        int itemCount = 0;
        for (CatListItem item : catListItems) {
            String title = "Image ".concat(String.valueOf(itemCount));
            String description =
                    "This is the description for ".concat(title).concat(" It's a really cool image, bask in it's gloriousness");
            catEntryList.add(new CatEntry(item.getUrl(), title, description, item.getId()));
            itemCount++;
        }
        return catEntryList;
    }
}

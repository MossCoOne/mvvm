package com.mossco.za.mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface CatDao {

    @Query("SELECT * FROM cat")
   LiveData< List<CatEntry> >retrieveListOfAllCats();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCatEntry(CatEntry... catEntries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCatEntries(List<CatEntry> catEntries);
}

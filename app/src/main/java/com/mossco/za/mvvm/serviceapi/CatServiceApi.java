package com.mossco.za.mvvm.serviceapi;

import com.mossco.za.mvvm.catlist.model.CatListItem;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface CatServiceApi {

    //https://api.thecatapi.com/api/images/get?format=json&results_per_page=100&size=small&type=png
    @GET("images/get?format=json&results_per_page=100&size=small&type=png")
    Call<List<CatListItem>> getListOfCats();
}

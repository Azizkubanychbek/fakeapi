package com.azyu.myfakeshop.remoteData;


import com.azyu.myfakeshop.models.ModelM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("products")
    Call<List<ModelM>> getStoreProducts();
}

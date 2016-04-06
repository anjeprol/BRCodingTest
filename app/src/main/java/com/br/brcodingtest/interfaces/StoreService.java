package com.br.brcodingtest.interfaces;

import com.br.brcodingtest.models.Stores;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoreService {
    @GET("/BR_Android_CodingExam_2015_Server/stores.json")
    Call<Stores> getListOfStores();
}

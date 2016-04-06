package com.br.brcodingtest.network;

import com.br.brcodingtest.interfaces.StoreService;
import com.br.brcodingtest.models.Stores;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController {
    private static final String BASE_URL = "http://sandbox.bottlerocketapps.com";
    private final NetworkListener listener;
    private StoreService service;

    public NetworkController(final NetworkListener listener) {
        this.listener = listener;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        this.service = retrofit.create(StoreService.class);
    }

    public void getListOfStores() {
        Call<Stores> call = null;
        call = service.getListOfStores();
        call.enqueue(new Callback<Stores>() {
            @Override
            public void onResponse(Call<Stores> call, Response<Stores> response) {
                int statusCode = response.code();
                Stores responseObj = response.body();
                listener.onSuccess(responseObj);
            }

            @Override
            public void onFailure(Call<Stores> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public interface NetworkListener {
        void onSuccess(Object data);

        void onFailure(String errorMsg);
    }
}

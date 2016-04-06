package com.br.brcodingtest.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.br.brcodingtest.R;
import com.br.brcodingtest.adapters.StoreAdapter;
import com.br.brcodingtest.models.Store;
import com.br.brcodingtest.models.Stores;
import com.br.brcodingtest.network.NetworkController;
import com.br.brcodingtest.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NetworkController.NetworkListener {

    private RecyclerView recyclerView;
    private StoreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        fetchDataFromCache();
    }

    private void setAdapter() {
        mAdapter = new StoreAdapter(this, new ArrayList<Store>());
        recyclerView.setAdapter(mAdapter);
    }

    private void fetchData() {
        if (AppUtil.isNetworkAvailable(this)) {
            new NetworkController(this).getListOfStores();
        } else {
            onFailure("Please check internet connectivity");
        }
    }

    private void initComponents() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setAdapter();
    }


    @Override
    public void onSuccess(final Object data) {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Stores responseObject = (Stores) data;
                List<Store> stores = responseObject.getStores();
                for (Store store : stores) {
                    store.save();
                }
                populateUI(stores);
            }
        });
    }

    @Override
    public void onFailure(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        findViewById(R.id.progress).setVisibility(View.GONE);
    }

    protected void fetchDataFromCache() {
        List<Store> dataList = Store.listAll(Store.class);
        if (dataList.size() > 0) {
            populateUI(dataList);
        } else {
            fetchData();
        }
    }

    private void populateUI(List<Store> dataList) {
        findViewById(R.id.progress).setVisibility(View.GONE);
        mAdapter.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }
}

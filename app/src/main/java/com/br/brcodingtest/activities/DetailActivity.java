package com.br.brcodingtest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.brcodingtest.R;
import com.br.brcodingtest.models.Store;
import com.br.brcodingtest.utils.BundleConstants;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView image;
    private TextView id;
    private TextView address;
    private TextView phone;
    private Store data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getData();
        initComponents();
        setTitle(data.getName());

    }


    private void initComponents() {
        id = (TextView) findViewById(R.id.id);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        image = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(data.getStoreLogoURL()).into(image);
        id.setText(data.getStoreID());
        phone.setText(data.getPhone());
        address.setText(data.getAddress() + ", " + data.getCity() + ", " + data.getState() + "\n(" + data.getLatitude() + "," + data.getLatitude() + ")");
    }


    public void getData() {
        Bundle bundle = getIntent().getExtras();
        data = (Store) bundle.getSerializable(BundleConstants.B_DATA);
    }
}

package com.br.brcodingtest.viewholders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.brcodingtest.R;
import com.br.brcodingtest.activities.DetailActivity;
import com.br.brcodingtest.models.Store;
import com.br.brcodingtest.utils.BundleConstants;
import com.squareup.picasso.Picasso;

public class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context mContext;
    private final ImageView imageView;
    private final TextView phone;
    private final TextView address;
    private Store data;

    public StoreViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.itemView.setOnClickListener(this);
        this.imageView = (ImageView)itemView.findViewById(R.id.image);
        this.phone = (TextView)itemView.findViewById(R.id.phone);
        this.address = (TextView)itemView.findViewById(R.id.address);
    }

    public void invalidate(Store mData) {
        setData(mData);
        phone.setText(data.getPhone());
        address.setText(data.getAddress());
        Picasso.with(mContext).load(data.getStoreLogoURL()).into(imageView);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleConstants.B_DATA, getData());
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public Store getData() {
        return data;
    }

    public void setData(Store data) {
        this.data = data;
    }
}


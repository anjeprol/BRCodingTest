package com.br.brcodingtest.models;

import java.util.ArrayList;
import java.util.List;

public class Stores {
    private List<Store> stores = new ArrayList<Store>();

    /**
     * @return The stores
     */
    public List<Store> getStores() {
        return stores;
    }

    /**
     * @param stores The stores
     */
    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}

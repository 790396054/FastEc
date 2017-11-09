package com.app.gmm.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by gmm on 2017/11/9.
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    protected String getJsonData(){
        if (mJsonData == null || mJsonData.length() == 0) {
            throw new NullPointerException("JsonData Is Null!!!");
        }
        return mJsonData;
    }

    public void clear() {
        ENTITIES.clear();
    }
}

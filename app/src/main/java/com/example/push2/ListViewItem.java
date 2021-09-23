package com.example.push2;


import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Comparator;


/**
 * Created by taewoo on 2019-11-16.
 */

public class ListViewItem {


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ListViewItem(String value) {
        this.value = value;
    }

    @SerializedName("ymd")
    @Expose
    String value;


}
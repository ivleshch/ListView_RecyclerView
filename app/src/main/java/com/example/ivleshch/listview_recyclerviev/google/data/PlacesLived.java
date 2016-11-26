package com.example.ivleshch.listview_recyclerviev.google.data;

import com.google.gson.annotations.SerializedName;


public class PlacesLived {

    @SerializedName("value")
//    @Expose
    private String value;
    @SerializedName("primary")
//    @Expose
    private boolean primary;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public PlacesLived() {
    }
}
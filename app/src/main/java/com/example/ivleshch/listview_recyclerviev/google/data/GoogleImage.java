package com.example.ivleshch.listview_recyclerviev.google.data;

import com.google.gson.annotations.SerializedName;

public class GoogleImage {

    @SerializedName("url")
//    @Expose
    private String url;

    public GoogleImage() {
    }

    @SerializedName("isDefault")
//    @Expose
    private boolean isDefault;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
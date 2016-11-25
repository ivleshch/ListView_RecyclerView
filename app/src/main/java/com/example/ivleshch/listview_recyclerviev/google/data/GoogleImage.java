package com.example.ivleshch.listview_recyclerviev.google.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GoogleImage {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("isDefault")
    @Expose
    private boolean isDefault;

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The isDefault
     */
    public boolean isIsDefault() {
        return isDefault;
    }

    /**
     *
     * @param isDefault
     * The isDefault
     */
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
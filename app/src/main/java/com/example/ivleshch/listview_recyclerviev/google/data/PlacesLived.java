package com.example.ivleshch.listview_recyclerviev.google.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PlacesLived {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("primary")
    @Expose
    private boolean primary;

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     * The primary
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     *
     * @param primary
     * The primary
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

}
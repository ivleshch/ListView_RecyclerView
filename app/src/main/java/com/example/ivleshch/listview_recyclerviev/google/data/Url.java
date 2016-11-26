package com.example.ivleshch.listview_recyclerviev.google.data;

import com.google.gson.annotations.SerializedName;


public class Url {

    @SerializedName("value")
//    @Expose
    private String value;
    @SerializedName("type")
//    @Expose
    private String type;
    @SerializedName("label")
//    @Expose
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public Url() {
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

package com.example.ivleshch.listview_recyclerviev.google.data;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("familyName")
//    @Expose
    private String familyName;
    @SerializedName("givenName")
//    @Expose
    private String givenName;

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Name() {
    }
}

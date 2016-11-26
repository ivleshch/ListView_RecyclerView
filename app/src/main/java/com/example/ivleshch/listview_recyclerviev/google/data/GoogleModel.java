package com.example.ivleshch.listview_recyclerviev.google.data;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GoogleModel {

    @SerializedName("kind")
//    @Expose
    private String kind;
    @SerializedName("etag")
//    @Expose
    private String etag;
    @SerializedName("birthday")
//    @Expose
    private String birthday;
    @SerializedName("gender")
//    @Expose
    private String gender;
    @SerializedName("urls")
//    @Expose
    private List<Url> urls = new ArrayList<Url>();
    @SerializedName("objectType")
//    @Expose
    private String objectType;
    @SerializedName("id")
//    @Expose
    private String id;
    @SerializedName("displayName")
//    @Expose
    private String displayName;
    @SerializedName("name")
//    @Expose
    private Name name;
    @SerializedName("url")
//    @Expose
    private String url;
    @SerializedName("image")
//    @Expose
    private GoogleImage image;
    @SerializedName("placesLived")
//    @Expose
    private List<PlacesLived> placesLived = new ArrayList<PlacesLived>();
    @SerializedName("isPlusUser")
//    @Expose
    private boolean isPlusUser;

    public GoogleModel() {
    }

    @SerializedName("circledByCount")
//    @Expose
    private int circledByCount;
    @SerializedName("verified")
//    @Expose
    private boolean verified;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GoogleImage getImage() {
        return image;
    }

    public void setImage(GoogleImage image) {
        this.image = image;
    }

    public List<PlacesLived> getPlacesLived() {
        return placesLived;
    }

    public void setPlacesLived(List<PlacesLived> placesLived) {
        this.placesLived = placesLived;
    }

    public boolean isIsPlusUser() {
        return isPlusUser;
    }

    public void setIsPlusUser(boolean isPlusUser) {
        this.isPlusUser = isPlusUser;
    }

    public int getCircledByCount() {
        return circledByCount;
    }

    public void setCircledByCount(int circledByCount) {
        this.circledByCount = circledByCount;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
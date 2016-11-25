package com.example.ivleshch.listview_recyclerviev.google.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;


public class GoogleModel {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("urls")
    @Expose
    private List<Url> urls = new ArrayList<Url>();
    @SerializedName("objectType")
    @Expose
    private String objectType;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private GoogleImage image;
    @SerializedName("placesLived")
    @Expose
    private List<PlacesLived> placesLived = new ArrayList<PlacesLived>();
    @SerializedName("isPlusUser")
    @Expose
    private boolean isPlusUser;
    @SerializedName("circledByCount")
    @Expose
    private int circledByCount;
    @SerializedName("verified")
    @Expose
    private boolean verified;

    /**
     *
     * @return
     * The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     * The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     * The etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     *
     * @param etag
     * The etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     *
     * @return
     * The birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     * The birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    /**
     *
     * @return
     * The urls
     */
    public List<Url> getUrls() {
        return urls;
    }

    /**
     *
     * @param urls
     * The urls
     */
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    /**
     *
     * @return
     * The objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     *
     * @param objectType
     * The objectType
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The name
     */
    public Name getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(Name name) {
        this.name = name;
    }

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
     * The image
     */
    public GoogleImage getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(GoogleImage image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The placesLived
     */
    public List<PlacesLived> getPlacesLived() {
        return placesLived;
    }

    /**
     *
     * @param placesLived
     * The placesLived
     */
    public void setPlacesLived(List<PlacesLived> placesLived) {
        this.placesLived = placesLived;
    }

    /**
     *
     * @return
     * The isPlusUser
     */
    public boolean isIsPlusUser() {
        return isPlusUser;
    }

    /**
     *
     * @param isPlusUser
     * The isPlusUser
     */
    public void setIsPlusUser(boolean isPlusUser) {
        this.isPlusUser = isPlusUser;
    }

    /**
     *
     * @return
     * The circledByCount
     */
    public int getCircledByCount() {
        return circledByCount;
    }

    /**
     *
     * @param circledByCount
     * The circledByCount
     */
    public void setCircledByCount(int circledByCount) {
        this.circledByCount = circledByCount;
    }

    /**
     *
     * @return
     * The verified
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     *
     * @param verified
     * The verified
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
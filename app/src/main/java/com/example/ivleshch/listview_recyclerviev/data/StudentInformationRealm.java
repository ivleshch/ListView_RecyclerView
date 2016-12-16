package com.example.ivleshch.listview_recyclerviev.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class StudentInformationRealm extends RealmObject {

    @PrimaryKey
    private int id;

    String name;
    String linkToGit;
    String linkToGoogle;
    String gitLogin;
    String idGoogle;
    String searchName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkToGit() {
        return linkToGit;
    }

    public void setLinkToGit(String linkToGit) {
        this.linkToGit = linkToGit;
    }

    public String getLinkToGoogle() {
        return linkToGoogle;
    }

    public void setLinkToGoogle(String linkToGoogle) {
        this.linkToGoogle = linkToGoogle;
    }

    public String getGitLogin() {
        return gitLogin;
    }

    public void setGitLogin(String gitLogin) {
        this.gitLogin = gitLogin;
    }

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName.toLowerCase();
    }
}

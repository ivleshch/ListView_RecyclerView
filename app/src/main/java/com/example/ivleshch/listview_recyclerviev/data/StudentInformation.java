package com.example.ivleshch.listview_recyclerviev.data;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class StudentInformation {

    String name;
    String linkToGit;
    String linkToGoogle;
    String gitLogin;
    String idGoogle;


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

    public StudentInformation(String name, String linkToGit, String linkToGoogle, String gitLogin, String idGoogle) {
        this.name = name;
        this.linkToGit = linkToGit;
        this.linkToGoogle = linkToGoogle;
        this.gitLogin = gitLogin;
        this.idGoogle = idGoogle;
    }
}

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

    public StudentInformation(String name, String linkToGit, String linkToGoogle, String gitLogin, String idGoogle) {
        this.name = name;
        this.linkToGit = linkToGit;
        this.linkToGoogle = linkToGoogle;
        this.gitLogin = gitLogin;
        this.idGoogle = idGoogle;
    }

    public String getGitLogin() {
        return gitLogin;
    }

    public String getName() {
        return name;
    }

    public String getLinkToGit() {
        return linkToGit;
    }

    public String getLinkToGoogle() {
        return linkToGoogle;
    }

    public String getIdGoogle() {
        return idGoogle;
    }
}

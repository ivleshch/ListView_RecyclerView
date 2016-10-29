package com.example.ivleshch.listview_recyclerviev;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class StudentInformation {
    String name;
    String linkToGit;
    String linkToGoogle;

    public StudentInformation(String name, String linkToGit, String linkToGoogle) {
        this.name = name;
        this.linkToGit = linkToGit;
        this.linkToGoogle = linkToGoogle;
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
}

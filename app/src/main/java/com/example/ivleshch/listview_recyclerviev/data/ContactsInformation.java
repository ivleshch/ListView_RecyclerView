package com.example.ivleshch.listview_recyclerviev.data;

/**
 * Created by Ivleshch on 25.11.2016.
 */
public class ContactsInformation {
    String name;
    String phoneNumber;

    public ContactsInformation(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

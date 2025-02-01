package com.example.namesapp;

public class NameModel {
    private String name;
    private String birthday;
    private String meaning;

    public NameModel(String name, String birthday, String meaning) {
        this.name = name;
        this.birthday = birthday;
        this.meaning = meaning;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getMeaning() {
        return meaning;
    }
}

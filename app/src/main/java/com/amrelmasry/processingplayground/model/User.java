package com.amrelmasry.processingplayground.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Amr Elmasry on 16/09/17.
 */

public class User {

    @NonNull
    private String name;
    private int age;
    @Nullable
    private String photoUrl;

    public User(@NonNull String name, int age, @Nullable String photoUrl) {
        this.name = name;
        this.age = age;
        this.photoUrl = photoUrl;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Nullable
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(@Nullable String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
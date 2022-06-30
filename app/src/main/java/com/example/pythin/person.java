package com.example.pythin;

import android.net.Uri;

public class person {
    String Name;
    String Phone;
    String Email;
    String Address;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    Uri image;

    public person(String name, String phone, String email, String address, Uri image) {
        Name = name;
        Phone = phone;
        Email = email;
        Address = address;
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}

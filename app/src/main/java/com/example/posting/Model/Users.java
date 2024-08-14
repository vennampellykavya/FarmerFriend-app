package com.example.posting.Model;



public class Users
{
    private String name, phone, password, image, address, uid;

    public Users()
    {

    }

    public Users(String name, String phone, String password, String image, String address,String uid) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.address = address;
        this.uid=uid;
    }

    public String getUserId() {
        return uid;
    }

    public void setUserId(String userId) {
        this.uid = userId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

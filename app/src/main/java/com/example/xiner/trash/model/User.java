package com.example.xiner.trash.model;

import java.io.Serializable;

/**
 * Created by peng on 15-3-16.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String qq;
    private String address;
    private String tag;//0代表普通的用户，1代表商户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

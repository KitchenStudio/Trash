package com.example.xiner.trash.model;

import java.io.Serializable;

/**
 * Created by peng on 15-3-18.
 */
public class Waste implements Serializable {

    private String id;
    private String uid;
    private String iname;
    private String desc;
    private String uname;
    private String phone;
    private String qq;
    private String address;
    private String createTime;

    public Waste() {

    }

    public Waste(String id, String iname) {
        this.id = id;
        this.iname = iname;
    }

    public Waste(String id) {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

package com.example.xiner.trash.model;

import java.io.Serializable;

/**
 * Created by peng on 15-3-18.
 */
public class Waste implements Serializable {

    private String id;
    //private String uid;
    private String uname;
    private String gname;
    private String description;
    private String status;//0未处理1正在处理2已回收3已删除
    private String time;//发布时间
    private String place;//代表地点的字符串，不能用于计算
    private double longitude;//经度
    private double parallel;//纬度


    private String catagory;
    private String u_done;
    private String d_done;
    private String gphone;

    public Waste() {

    }

    public Waste(String id, String gname) {
        this.id = id;
        this.gname = gname;
    }

    public Waste(String id) {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getParallel() {
        return parallel;
    }

    public void setParallel(double parallel) {
        this.parallel = parallel;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getU_done() {
        return u_done;
    }

    public void setU_done(String u_done) {
        this.u_done = u_done;
    }

    public String getD_done() {
        return d_done;
    }

    public void setD_done(String d_done) {
        this.d_done = d_done;
    }

    public String getGphone() {
        return gphone;
    }

    public void setGphone(String gphone) {
        this.gphone = gphone;
    }
}

package com.example.xiner.trash.model;

import java.io.Serializable;

/**
 * Created by peng on 15-3-18.
 */
public class Commodity implements Serializable {

    private String id;
    //private String uid;
    private String uname;
    private String iname;
    private String price;
    private String old;//新旧程度recency
    private String place;
    private String catagory;
    private String uphone;
    private String qq;
    private String description;
    private String time;


    public Commodity() {

    }

    public Commodity(String id, String iname) {
        this.id = id;
        this.iname = iname;

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

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


//public static final int SUMMER_TIME = 1;
//public static final int WINTER_TIME = 2;
//public static final int WEEK_DAY = 0;
//public static final int NO_WEEK_DAY = 1;
//
//private String Time = null;
//
//private static final String TAG = SchoolBusModel.class.getSimpleName();
//
//private static class SchoolBusModelHolder {
//    public static SchoolBusModel model = new SchoolBusModel();
//}
//
//    public static SchoolBusModel getInstance() {
//        return SchoolBusModelHolder.model;
//    }
//
//    public List<BusInfo> getBus(Context context, String from, String to,
//                                int busType, int schedule) {
//        List<BusInfo> buslist = new ArrayList<BusInfo>();
//        DataBaseHelper dbhelper = new DataBaseHelper(context);
//        SQLiteDatabase sqlDB = dbhelper.getReadableDatabase();
//        Log.d(TAG, from + " " + to);
//
//        String sql;
//        if (schedule == SUMMER_TIME) {
//
//            sql = "select * from summer_time where bus_type = ? and id in (select id from search_table where from_=? and to_=?)";
//        } else {
//
//            sql = "select * from winter_time where bus_type = ? and id in (select id from search_table where from_=? and to_=?)";
//        }
//
//        Cursor cursor = sqlDB.rawQuery(sql, new String[] { busType + "", from,
//                to });
//        Log.v(TAG, sql + "from:" + from + "to:" + to + "busType:" + busType);
//
//        while (cursor.moveToNext()) {
//            BusInfo businfo = new BusInfo();
//            businfo.setStartTime(cursor.getString(cursor
//                    .getColumnIndex("start_time")));
//            businfo.setEndTime(cursor.getString(cursor
//                    .getColumnIndex("end_time")));
//            businfo.setBusType(cursor.getInt(cursor.getColumnIndex("bus_type")));
//            businfo.setFrom(new Place(cursor.getString(cursor
//                    .getColumnIndex("bus_from")), cursor.getString(cursor
//                    .getColumnIndex("from_desc"))));
//            businfo.setTo(new Place(cursor.getString(cursor
//                    .getColumnIndex("bus_to")), cursor.getString(cursor
//                    .getColumnIndex("to_desc"))));
//            businfo.setBusBetween(cursor.getString(cursor
//                    .getColumnIndex("bus_between")));
//            buslist.add(businfo);
//        }
//        cursor.deactivate();
//        sqlDB.close();
//        Collections.sort(buslist);
//        return buslist;
//    }
//
//    public int getSchedule(Context context) {
//        DataBaseHelper dbhelper = new DataBaseHelper(context);
//        SQLiteDatabase sqlDB = dbhelper.getReadableDatabase();
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
//        String datetime = sdf.format(date);
//        Log.v(TAG, datetime);
//        String sql = "select id from summer_winter where start_<= ? and end_ >= ?";
//        Cursor cursor = sqlDB
//                .rawQuery(sql, new String[] { datetime, datetime });
//        int schedule = -1;
//        if (cursor.moveToFirst()) {
//            schedule = cursor.getInt(cursor.getColumnIndex("id"));
//
//        }
//        cursor.deactivate();
//        sqlDB.close();
//        Log.v(TAG, "type:" + schedule);
//        return schedule;
//    }
//}
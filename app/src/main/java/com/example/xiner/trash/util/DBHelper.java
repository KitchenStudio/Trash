package com.example.xiner.trash.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.model.Waste;

/**
 * Created by peng on 15-3-18.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "green.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_USER = "CREATE TABLE userTable (id VARCHAR(10) PRIMARY KEY," +
            "name VARCHAR(10), " +
            "phone VARCHAR(20), " +
            "qq VARCHAR(20), " +
            "address VARCHAR(30), " +
            "tag VARCHAR(4) );";
    private static final String CREATE_TABLE_COMMODITY = "CREATE TABLE commodityTable (id VARCHAR(10) PRIMARY KEY," +
            "uid VARCHAR(10), " +
            "iname VARCHAR(10), " +
            "price VARCHAR(10), " +
            "recency VARCHAR(10), " +
            "desc VARCHAR(100), " +
            "uname VARCHAR(10), " +
            "phone VARCHAR(20), " +
            "qq VARCHAR(20), " +
            "address VARCHAR(30)," +
            "catagory VARCHAR(10)," +
            "createTime VARCHAR(20));";

    private static final String CREATE_TABLE_WASTE = "CREATE TABLE wasteTable (id VARCHAR(10) PRIMARY KEY," +
            "iname VARCHAR(10), " +
            "desc VARCHAR(100), " +
            "uname VARCHAR(10), " +
            "phone VARCHAR(20), " +
            "qq VARCHAR(20), " +
            "address VARCHAR(30)," +
            "catagory VARCHAR(10) );";

    private Context context;
    SQLiteDatabase db;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_COMMODITY);
        db.execSQL(CREATE_TABLE_WASTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCommodity(Commodity commodity) {
        String id = commodity.getId();
        String uid = commodity.getUid();
        String iname = commodity.getIname();
        String price = commodity.getPrice();
        String recency = commodity.getRecency();
        String desc = commodity.getDesc();
        String uname = commodity.getUname();
        String phone = commodity.getPhone();
        String qq = commodity.getQq();
        String address = commodity.getAddress();
        String catagory = commodity.getCatagory();
        String createTime = commodity.getCreateTime();

        String sql = "INSERT INTO commodityTable VALUES ('" + id + "', '" + uid + "', '" + iname + "' ,'" + price + "', '"
                + recency + "', '" + desc + "', '" + uname + "', '" + phone + "', '" + qq + "', '" + address + "', '" + catagory +
                "', '" + createTime + "');";
        db.execSQL(sql);
    }

    public void addWaste(Waste waste) {
        String id = waste.getId();
        String uid = waste.getUid();
        String iname = waste.getIname();
        String desc = waste.getDesc();
        String uname = waste.getUname();
        String phone = waste.getPhone();
        String qq = waste.getQq();
        String address = waste.getAddress();
        String createTime = waste.getCreateTime();

        String sql = "INSERT INTO commodityTable VALUES ('" + id + "', '" + uid + "', '" + iname + "' ,'"
                + desc + "', '" + uname + "', '" + phone + "', '" + qq + "', '" + address + "', '" + createTime
                + "');";
        db.execSQL(sql);
    }

    public void open() {
        db = getWritableDatabase();
    }

    public void close() {
        db.close();
        this.close();
    }

}

//private final static String DATABASE_NAME = "daka.db";
//private final static String TABLE_NAME = "task_table";
//private final static int DATABASE_VERSION = 1;
//
//private final static String TASK_ID = "task_id";
//private final static String TASK_CONTENT = "task_content";
//private final static String TASK_TIME = "task_time";
//private final static String Task_STATUS = "task_status";
//
//    public int getSize() {
//        db = getReadableDatabase();
//        Cursor c = db.rawQuery("select count(*) from " + TABLE_NAME, null);
//        c.moveToFirst();
//        int size = c.getInt(0);
//        c.close();
//        db.close();
//        return size;
//    }
//    public void insert(String uuid, String content, int time) {
//        db = getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put(TASK_ID, uuid);
//        cv.put(TASK_CONTENT, content);
//        cv.put(TASK_TIME, time);
//
//        db.insert(TABLE_NAME, null, cv);
//        db.close();
//    }
//    public void delete(String uuid) {
//        db = getWritableDatabase();
//        String sql = "DELETE FROM " + TABLE_NAME + " where task_id = " + "'"
//                + uuid + "'";
//        db.execSQL(sql);
//        db.close();
//    }
//    public void updateStatus(String uuid,int status) {
//        db = getWritableDatabase();
//        String where = TASK_ID + " = ?";
//        String[] whereValue = {uuid};
//
//        ContentValues cv = new ContentValues();
//        cv.put(Task_STATUS, status);
//        db.update(TABLE_NAME, cv, where, whereValue);
//        db.close();
//    }
//    public void changeItem(String uuid, String content) {
//        db = getWritableDatabase();
//        String where = TASK_ID + " = ?";
//        String[] whereValue = {uuid};
//
//        ContentValues cv = new ContentValues();
//        cv.put(TASK_CONTENT, content);
//        db.update(TABLE_NAME, cv, where, whereValue);
//        db.close();
//
//    }
//
//    public ArrayList<MessionMessageItem> getList() {
//        ArrayList<MessionMessageItem> lists = new ArrayList<MessionMessageItem>();
//        db = getWritableDatabase();
//
//        String sql = "SELECT * from " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            MessionMessageItem task = new MessionMessageItem();
//            task.setUuid(cursor.getString(0));
//            task.setMsg(cursor.getString(1));
//            task.setCreateTime(cursor.getInt(2));
//            task.setStatus(cursor.getInt(3));
//            lists.add(task);
//        }
//        db.close();
//        return lists;
//    }
//
//}

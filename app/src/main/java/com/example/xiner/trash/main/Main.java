package com.example.xiner.trash.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.xiner.trash.model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by peng on 15-3-16.
 */
public class Main extends Application {

    private static final String SDPATH = Environment.getExternalStorageState() + "";
    private static final String LOGIN = "login";
    private static Main app;//静态单例
    private User user;
    private boolean login = false;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Main getInstance() {
        return app;//获得单例的Main对象
    }

    // 键值对仓库 包括文件标记、个人信息
    public SharedPreferences getDataStore() {
        return this.getSharedPreferences("Green", MODE_PRIVATE);
    }

    public void setUser(User user) {
        this.user = user;
        //保存用户的各种信息
        getDataStore().edit().putString("id", user.getId())
                .putString("name", user.getName())
                .putString("phone", user.getPhone())
                .putString("address", user.getAddress())
                .putString("qq", user.getQq())
                .putString("tag", user.getTag()).commit();
    }

    public User getUser() {
        return user;
    }

    // 头像写到sdcard中
    public void setPicToView(Bitmap mBitmap, String path) {

        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "/head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        getDataStore().edit().putBoolean("ifheadstore",true).commit();
    }

    public void clear() {

    }

<<<<<<< HEAD
    public Bitmap getLoacalBitmap(String url) {
        FileInputStream fis;
        Bitmap bit = null;
        try {
            fis = new FileInputStream(url);
            bit = BitmapFactory.decodeStream(fis);
            fis.close();

            // /锟斤拷锟斤拷转锟斤拷为Bitmap图片
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bit;
    }

=======
    public boolean isLogin() {
        return login == true;

    }

    public void setLogin(boolean login) {
        this.login = login;
    }
>>>>>>> 7327125589cac2d4eb60bf0c49ea26481fd5a28f
}


//    public void setAskAndAnswer(String filename, List<Question> list) {
//        try {
//            FileOutputStream out = openFileOutput(filename, MODE_PRIVATE);
//            ObjectOutputStream os = new ObjectOutputStream(out);
//            os.writeObject(list);
//            os.close();
//            out.close();
//            getDataStore().edit().putBoolean(filename, true).commit();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public List<Question> getAskandAnswer(String filename) {
//        if (getDataStore().getBoolean(filename, false)) {
//            try {
//                FileInputStream in = openFileInput(filename);
//                ObjectInputStream is = new ObjectInputStream(in);
//                List<Question> list = (List<Question>) is.readObject();
//                is.close();
//                in.close();
//                return list;
//            } catch (FileNotFoundException e) {
//            } catch (StreamCorruptedException e) {
//            } catch (IOException e) {
//            } catch (ClassNotFoundException e) {
//            }
//        } else {
//            return new ArrayList<Question>();
//        }
//        return new ArrayList<Question>();
//    }
//
//    public List<SignRelease> getRankandComment(String filename) {
//        if (getDataStore().getBoolean(filename, false)) {
//            try {
//                FileInputStream in = openFileInput(filename);
//                ObjectInputStream is = new ObjectInputStream(in);
//                List<SignRelease> list = (List<SignRelease>) is.readObject();
//                is.close();
//                in.close();
//                return list;
//            } catch (FileNotFoundException e) {
//            } catch (StreamCorruptedException e) {
//            } catch (IOException e) {
//            } catch (ClassNotFoundException e) {
//            }
//        } else {
//            return new ArrayList<SignRelease>();
//        }
//        return new ArrayList<SignRelease>();
//    }
//
//    public String[] getCurriculumArray() {
//        if (curriculumArray != null)
//            return curriculumArray;
//
//        if (getDataStore().getBoolean(CURRICULUM, false)) {
//            Log.v("Main", "true");
//            try {
//                FileInputStream in = openFileInput(CURRICULUM);
//                ObjectInputStream is = new ObjectInputStream(in);
//                curriculumArray = (String[]) is.readObject();
//                is.close();
//                in.close();
//            } catch (FileNotFoundException e) {
//            } catch (StreamCorruptedException e) {
//            } catch (IOException e) {
//            } catch (ClassNotFoundException e) {
//            }
//        } else {
//            curriculumArray = new String[35];
//            return curriculumArray;
//        }
//        return curriculumArray;
//    }
//
//    // 得到颜色的数组
//
//    public void number(List<Lesson> lessonList) {
//        String namec[] = new String[35];
//        boolean ifrepeat[] = new boolean[35];
//        // all = new ArrayList<List<Integer>>();
//        for (int ii = 0; ii < 35; ii++) {
//            ifrepeat[ii] = false;
//        }
//
//        for (Lesson lesson : lessonList) {
//            int position = lesson.getClassDayOfWeek() + 7
//                    * (lesson.getClassDayOfTime() - 1) - 1;
//            if (namec[position] != null) {
//                namec[position] += lesson.getClassName();
//
//            } else {
//                namec[position] = lesson.getClassName();
//            }
//        }
//        for (int i = 0; i < 35; i++) {
//            List<Integer> save = new ArrayList<Integer>();
//            if (namec[i] != null && ifrepeat[i] == false) {
//                ifrepeat[i] = true;
//                String temp = namec[i];
//                save.add(i);
//                for (int m = i + 1; m < 35; m++) {
//                    if (namec[m] != null && ifrepeat[m] == false) {
//                        if (namec[m].equals(temp)) {
//                            ifrepeat[m] = true;
//                            save.add(m);
//                        }
//                    }
//
//                }
//                all.add(save);
//            }
//
//        }
//        Log.v("all", all.size() + "allsize");
//    }
//
//    public void clearAll() {
//        getDataStore().edit().remove("COMPULSORY").remove("username")
//                .remove("password").remove("stuid").remove("stuname")
//                .remove("academy").remove("specialty").remove("year_1")
//                .remove("year_2").remove("year_3").remove("year_4")
//                .remove("FACEIMAGE").remove("FACESTORE").remove("edittalk")
//                .remove("editsex").remove("editaim").remove("editname")
//                .remove("ifsign").remove("signorder").remove("signrank")
//                .remove("signcontinue").remove("lock").remove("Info_name")
//                .remove("Info_sex").remove("Info_talktome").remove("Info_aim")
//                .remove("iflock").remove("faceimagepath").remove("whichfrag")
//                .remove("RANKANDCOMMENT").remove("HOMEWORK").remove("signday")
//                .remove("signmonth").remove("signall").remove("isLogin")
//                .remove("signif").remove("flagaskoldest")
//                .remove("APPPACKAGENAME").remove("curriculum")
//                .remove("ASKANDANSWER").commit();
//
//    }
//

//
//    // 关于作业
//    public void setHomework(String filename, ArrayList<MyHomework> homelist) {
//        try {
//            FileOutputStream out = openFileOutput(filename, MODE_PRIVATE);
//            ObjectOutputStream os = new ObjectOutputStream(out);
//            os.writeObject(homelist);
//            os.close();
//            out.close();
//            getDataStore().edit().putBoolean(filename, true).commit();
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<MyHomework> getHomework(String filename) {
//        if (getDataStore().getBoolean(filename, false)) {
//            try {
//                FileInputStream in = openFileInput(filename);
//                ObjectInputStream is = new ObjectInputStream(in);
//                ArrayList<MyHomework> list = (ArrayList<MyHomework>) is
//                        .readObject();
//                is.close();
//                in.close();
//                return list;
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (StreamCorruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        } else {
//            return new ArrayList<MyHomework>();
//        }
//        return new ArrayList<MyHomework>();
//    }
//
//    public TreeMap<Integer, List<Course>> getMap() {
//        return readFromFile("COMPULSORY");
//    }
//
//    public List<Course> getCompulsoryList(int semester) {
//        return getMap().get(semester);
//
//    }
//
//    public void setCompulsoryList(TreeMap<Integer, List<Course>> compulsoryList) {
//        if (compulsoryList == null) {
//            compulsoryList = new TreeMap<Integer, List<Course>>();
//        } else {
//            writeToFile("COMPULSORY", compulsoryList);
//
//        }
//    }
//
//    private void writeToFile(String filename,
//                             TreeMap<Integer, List<Course>> treemap) {
//        try {
//            FileOutputStream out = openFileOutput(filename, MODE_PRIVATE);
//            ObjectOutputStream os = new ObjectOutputStream(out);
//            os.writeObject(treemap);
//            os.close();
//            out.close();
//            getDataStore().edit().putBoolean(filename, true).commit();
//        } catch (FileNotFoundException e) {
//            System.out.println(e + "yi chang shi");
//        } catch (IOException e) {
//            System.out.println("yi chang shi" + e);
//        }
//
//    }
//
//    private TreeMap<Integer, List<Course>> readFromFile(String filename) {
//        if (getDataStore().getBoolean(filename, false)) {
//            try {
//                FileInputStream in = openFileInput(filename);
//                ObjectInputStream is = new ObjectInputStream(in);
//                TreeMap<Integer, List<Course>> map = (TreeMap<Integer, List<Course>>) is
//                        .readObject();
//                is.close();
//                in.close();
//                return map;
//            } catch (FileNotFoundException e) {
//            } catch (StreamCorruptedException e) {
//            } catch (IOException e) {
//            } catch (ClassNotFoundException e) {
//            }
//        } else {
//            return new TreeMap<Integer, List<Course>>();
//        }
//        return new TreeMap<Integer, List<Course>>();
//    }
//
//    // 超级学霸模式，获取时间
//    public int getHour() {
//        return date.getHour();
//    }
//
//    public int getSecond() {
//        return date.getSecond();
//    }
//
//    public int getMinute() {
//        return date.getMinute();
//    }
//
//    // 处理时间
//    public int caloffset() {
//        int offset;
//        if (getHour() < 20) {
//            offset = (19 - getHour()) * 60 * 60 * 1000 + (60 - getMinute())
//                    * 60 * 1000 + (60 - getSecond()) * 1000;
//        } else if (getHour() == 20 && getMinute() == 0 && getSecond() == 0) {
//            offset = 0;
//        } else {
//            offset = (43 - getHour()) * 60 * 60 * 1000 + (60 - getMinute())
//                    * 60 * 1000 + (60 - getSecond()) * 1000;
//        }
//        System.out.println("offset wei" + offset);
//        return offset;
//    }
//
//    public int[] getIntColors() {
//
//        if (getDataStore().getBoolean(COLORS, false)) {
//            try {
//                FileInputStream in = openFileInput(COLORS);
//                ObjectInputStream is = new ObjectInputStream(in);
//                intColors = (int[]) is.readObject();
//                is.close();
//                in.close();
//            } catch (FileNotFoundException e) {
//            } catch (StreamCorruptedException e) {
//            } catch (IOException e) {
//            } catch (ClassNotFoundException e) {
//            }
//        } else {
//            if (getDataStore().getBoolean(LOGIN, false)) {
//                generateRandomColors();
//            }
//        }
//        return intColors;
//    }
//
//    public void generateRandomColors() {
//        int a = 0;
//        intColors = new int[35];
//        Log.v("allsize", all.size() + "allsizeg");
//        // Random ran = new Random();
//        for (int i = 0; i < all.size(); i++) {
//            for (int ii = 0; ii < all.get(i).size(); ii++) {
//                intColors[all.get(i).get(ii)] = a;
//            }
//            a++;
//        }
//
//        // for (int i = 0; i < 35; i++) {
//        // intColors[i] = ran.nextInt(7);
//        // }
//        try {
//            FileOutputStream out = openFileOutput(COLORS, MODE_PRIVATE);
//            ObjectOutputStream os = new ObjectOutputStream(out);
//            os.writeObject(intColors);
//            os.close();
//            out.close();
//            getDataStore().edit().putBoolean(COLORS, true).commit();
//        } catch (FileNotFoundException e) {
//        } catch (IOException e) {
//        }
//    }
//
//    public ConnectivityManager getConnectivityManager() {
//        return (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//    }

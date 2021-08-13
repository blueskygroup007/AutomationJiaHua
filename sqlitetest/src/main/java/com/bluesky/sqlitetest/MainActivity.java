package com.bluesky.sqlitetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "sqlite_test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //依靠DatabaseHelper带全部参数的构造函数创建数据库
/*        DBHelper dbHelper = new DBHelper(MainActivity.this, "tuoliu.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from device", null);
        if (cursor.getCount() != 0) {
            String outStr = "";
            while (cursor.moveToNext()) {
                Log.d(TAG, "serial=" + cursor.getInt(0) + "--tag=" + cursor.getString(1) + "--affect=" + cursor.getString(2));
            }
            cursor.close();  // 用完关闭cursor
            db.close();      // 用完关闭数据库

        }*/

        SQLiteDatabase db = LitePal.getDatabase();
/*        List<Device> devices = LitePal.findAll(Device.class, 1);
        for (Device device : devices
        ) {

            Log.d("test", device.toString());
        }*/
        Cursor cursor = db.rawQuery("select * from device", new String[]{});
        if (cursor.getCount() != 0) {
            String outStr = "";
            while (cursor.moveToNext()) {
                Log.d(TAG, "serial=" + cursor.getInt(0)
                        + "--tag=" + cursor.getString(1)
                        + "--affect=" + cursor.getString(2)
                        + "--parameter=" + cursor.getString(3)
                        + "--name=" + cursor.getString(4)
                        + "--range=" + cursor.getString(5)
                        + "--standard=" + cursor.getString(6)
                        + "--mode=" + cursor.getString(7)
                        + "--pipe=" + cursor.getString(8)
                        + "--type=" + cursor.getString(9)
                        + "--count=" + cursor.getString(10)
                        + "--install=" + cursor.getString(11)
                        + "--factory=" + cursor.getString(12)
                        + "--remark=" + cursor.getString(13)
                        + "--brand=" + cursor.getString(14)
                        + "--date=" + cursor.getString(15)

                );
            }
            cursor.close();  // 用完关闭cursor
            db.close();      // 用完关闭数据库

        }
    }
}
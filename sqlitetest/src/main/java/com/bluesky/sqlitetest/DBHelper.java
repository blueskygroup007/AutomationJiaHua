package com.bluesky.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author BlueSky
 * @date 2021/7/4
 * Description:
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据库sql语句
        String sql = "CREATE TABLE IF NOT EXISTS `${TABLE_NAME}` (`serial` INTEGER NOT NULL, `tag` TEXT, `affect` TEXT, `parameter` TEXT, `name` TEXT, `range` TEXT, `standard` TEXT, `mode` TEXT, `pipe` TEXT, `type` TEXT, `count` TEXT, `install` TEXT, `factory` TEXT, `remark` TEXT, `brand` TEXT, `date` TEXT, PRIMARY KEY(`serial`))";
        //String sq1 = "CREATE TABLE IF NOT EXISTS `${TABLE_NAME}` (`serial` INTEGER NOT NULL, `tag` TEXT, `affect` TEXT, `parameter` TEXT, `name` TEXT, `range` TEXT, `standard` TEXT, `mode` TEXT, `pipe` TEXT, `type` TEXT, `count` TEXT, `install` TEXT, `factory` TEXT, `remark` TEXT, `brand` TEXT, `date` TEXT, PRIMARY KEY(`serial`))";
        //执行sql语句
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

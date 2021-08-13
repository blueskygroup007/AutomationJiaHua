package com.bluesky.automationjiahua.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bluesky.automationjiahua.base.App;

import org.jetbrains.annotations.NotNull;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:设备数据库
 */
//将exportSchema设为false了。默认应该为true
@Database(entities = {Device.class}, version = 1, exportSchema = false)
public abstract class DeviceDataBase extends RoomDatabase {
    private static DeviceDataBase INSTANCE;

    public static synchronized DeviceDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            Migration migration_1_2 = new Migration(1, 2) {
                @Override
                public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
                    //增加字段的迁移策略
//                    database.execSQL("ALTER TABLE word ADD COLUMN sw_chinese_visiable INTEGER NOT NULL");
                }
            };

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DeviceDataBase.class, App.DATA_BASE_NAME)
                    //.createFromAsset(DATABASE_ASSETS_PATH)
                    //.addMigrations(migration_1_2)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }

    public abstract DeviceDao getDeviceDao();
}

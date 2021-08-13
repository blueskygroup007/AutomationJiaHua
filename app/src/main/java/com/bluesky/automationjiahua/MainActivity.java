package com.bluesky.automationjiahua;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bluesky.atuomationjiahua.R;
import com.bluesky.atuomationjiahua.databinding.ActivityMainBinding;
import com.bluesky.automationjiahua.database.DBHelper;
import com.bluesky.automationjiahua.database.Device;
import com.bluesky.automationjiahua.database.DeviceDao;
import com.bluesky.automationjiahua.database.DeviceDataBase;
import com.bluesky.automationjiahua.database.DeviceRepository;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static List<Device> mDeviceList;


    /**
     * 从sqlite数据库生成room新数据库
     */
    public void createDatabase() {
        List<String> columns = new ArrayList<>();
        columns.add("chuleng");
        columns.add("dianbujiaoyou");
        columns.add("gufengji");
        columns.add("jiaoyouanshui");
        columns.add("liuan");
        columns.add("tuoliu");
        columns.add("youku");
        columns.add("zhengan");
        columns.add("zhonglengxiben");

        //依靠DatabaseHelper带全部参数的构造函数创建数据库
        DBHelper dbHelper = new DBHelper(MainActivity.this, "total.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (String str :
                columns) {

            Cursor cursor = db.rawQuery("select * from " + str, null);
            if (cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    Log.d("test_TAG", "tag=" + cursor.getString(0) + "--affect=" + cursor.getString(1));
                    Device device = new Device(cursor.getString(0)
                            , cursor.getString(1)
                            , cursor.getString(2)
                            , cursor.getString(3)
                            , cursor.getString(4)
                            , cursor.getString(5)
                            , cursor.getString(6)
                            , cursor.getString(7)
                            , cursor.getString(8)
                            , cursor.getString(9)
                            , cursor.getString(10)
                            , cursor.getString(11)
                            , cursor.getString(12)
                            , cursor.getString(13)
                            , cursor.getString(14)
                            , str);
                    DeviceRepository repository = new DeviceRepository(MainActivity.this.getApplicationContext());
                    repository.insertDevices(device);
                }
                cursor.close();
            }
        }
        db.close();

    }

    /**
     * 修复新数据库中，tag和affect中含有回车,换行,制表符的问题
     */
    public void fixDeviceFormat() {
        DeviceDataBase db = DeviceDataBase.getDatabase(this.getApplicationContext());
        DeviceDao mDeviceDao = db.getDeviceDao();
        new getAllDevicesNoLivedataTask(mDeviceDao, getApplicationContext()).execute();


    }

    /**
     * 多表合一线程
     */
    static class getAllDevicesNoLivedataTask extends AsyncTask<Void, Void, List<Device>> {
        DeviceDao mDeviceDao;
        Context mContext;

        public getAllDevicesNoLivedataTask(DeviceDao dao, Context context) {
            mDeviceDao = dao;
            mContext = context;
        }

        @Override
        protected List<Device> doInBackground(Void... voids) {
            return mDeviceDao.getAllDevicesNoLivedata();
        }

        @Override
        protected void onPostExecute(List<Device> devices) {
            super.onPostExecute(devices);
            mDeviceList = devices;
            DeviceRepository repository = new DeviceRepository(mContext);
            //todo 应该包涵换行，再更新该项,但是，如果更改了tag。那么更新就无意义。因为主键已经变化了。
            //这里采用了删除旧行delete,增加新行,insert
            for (Device device :
                    devices) {
                String tag = device.getTag();
                String affect = device.getAffect();
                //如果tag包含回车,换行,删除旧条目,增加新条目
                if (tag.contains("\r") || tag.contains("\n") || tag.contains("/t")) {
                    repository.deleteDevices(device);
                    Log.d("delete****running:", device.getTag());

/*                    Log.d("delete----running:", device.getTag());
                    tag=tag.replaceAll("\r", "");
                    tag=tag.replaceAll("\n", "");
                    tag=tag.replaceAll("\t", "");
                    device.setTag(tag);

                    if (affect != null) {
                        affect=affect.replace("\r","");
                        affect=affect.replace("\n","");
                        affect=affect.replace("\t","");
                        device.setAffect(affect);
                    }
                    repository.insertDevices(device);
                    Log.d("insert****running:", device.getTag());*/
                }
            }
        }
    }

    /**
     * 权限申请框架
     */
    private void requestPermissions() {
        XXPermissions.with(this)
                // 申请安装包权限
                //.permission(Permission.REQUEST_INSTALL_PACKAGES)
                // 申请悬浮窗权限
                //.permission(Permission.SYSTEM_ALERT_WINDOW)
                // 申请通知栏权限
                //.permission(Permission.NOTIFICATION_SERVICE)
                // 申请系统设置权限
                //.permission(Permission.WRITE_SETTINGS)
                // 申请单个权限
                //.permission(Permission.RECORD_AUDIO)
                // 申请多个权限
                //.permission(Permission.Group.CALENDAR)
                .permission(Permission.Group.STORAGE)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            toast("获取外置存储卡读写权限成功");
                        } else {
                            toast("获取部分权限成功，但部分权限未正常授予");
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            toast("被永久拒绝授权，请手动授予外置存储卡读写权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(MainActivity.this, permissions);
                        } else {
                            toast("获取外置存储卡读写权限失败");
                        }
                    }
                });
    }

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //暂停申请读取外部存储卡权限,会报以下错误.
        //Please register the android:requestLegacyExternalStorage="true" attribute in the manifest file

        //requestPermissions();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(binding.getRoot(), "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //createDatabase();
                                fixDeviceFormat();
                            }
                        }).show();
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
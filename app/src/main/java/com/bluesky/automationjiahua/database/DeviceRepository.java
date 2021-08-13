package com.bluesky.automationjiahua.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import java.util.List;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:
 */
public class DeviceRepository {
    DeviceDao mDeviceDao;
    Context mContext;
    LiveData<List<Device>> mLiveData;
    String Tag = DeviceRepository.class.getSimpleName();

    public DeviceRepository(Context context) {
        mContext = context;
        DeviceDataBase db = DeviceDataBase.getDatabase(context);
        mDeviceDao = db.getDeviceDao();
        mLiveData = mDeviceDao.getAllDevices();
    }


    public void insertDevices(Device... devices) {
        new InsertTask(mDeviceDao).execute(devices);
    }

    public void deleteAllDevices() {
        new DeleteAllTask(mDeviceDao).execute();
    }

    public void deleteDevices(Device... devices) {
        new DeleteTask(mDeviceDao).execute(devices);
    }

    public void updateDevices(Device... devices) {
        new UpdateTask(mDeviceDao).execute(devices);
    }

    public LiveData<List<Device>> getAllDevices() {
        return mLiveData;
    }

    public LiveData<List<Device>> findDeviceByTag(String pattern) {
        return mDeviceDao.queryDevicesByTag("%" + pattern + "%");
    }

    public LiveData<List<Device>> findDeviceByAffect(String pattern) {
        return mDeviceDao.queryDevicesByAffect("%" + pattern + "%");
    }

    public LiveData<List<Device>> findDeviceByName(String pattern) {
        return mDeviceDao.queryDevicesByName("%" + pattern + "%");
    }

    public LiveData<List<Device>> findDeviceByStandard(String pattern) {
        return mDeviceDao.queryDevicesByStandard("%" + pattern + "%");
    }

    public LiveData<List<Device>> findDeviceByType(String pattern) {
        return mDeviceDao.queryDevicesByType("%" + pattern + "%");

    }

    public LiveData<List<Device>> findDeviceByPattern(String domain, String column, String keyWords) {
        if (!domain.isEmpty()) {
            domain = "domain='" + domain + "' and ";
        }
        column = column + " like ";
        keyWords = "'%" + keyWords + "%'";
        Log.d(Tag, "查询语句:  " + "select * from device where " + domain + column + keyWords);
        //return mDeviceDao.queryDevicesByPattern("where domain=" + domain + "and " + column + "like " + "%" + keyWords + "%");
        //return mDeviceDao.queryDevicesByPattern(domain + column + keyWords);
        SimpleSQLiteQuery query = new SimpleSQLiteQuery("select * from device where " + domain + column + keyWords);
        return mDeviceDao.rawQueryDevicesByPattern(query);
    }


    static class InsertTask extends AsyncTask<Device, Void, Void> {

        DeviceDao mDeviceDao;

        public InsertTask(DeviceDao deviceDao) {
            mDeviceDao = deviceDao;
        }

        @Override
        protected Void doInBackground(Device... devices) {
            mDeviceDao.insertAll(devices);
            return null;
        }
    }

    static class UpdateTask extends AsyncTask<Device, Void, Void> {

        DeviceDao mDeviceDao;

        public UpdateTask(DeviceDao deviceDao) {
            mDeviceDao = deviceDao;
        }

        @Override
        protected Void doInBackground(Device... devices) {
            mDeviceDao.updateDevices(devices);
            return null;
        }
    }

    static class DeleteTask extends AsyncTask<Device, Void, Integer> {

        DeviceDao mDeviceDao;

        public DeleteTask(DeviceDao deviceDao) {
            mDeviceDao = deviceDao;
        }

        @Override
        protected Integer doInBackground(Device... devices) {

            mDeviceDao.deleteDevices(devices);
            return null;
        }
    }

    static class DeleteAllTask extends AsyncTask<Void, Void, Void> {

        DeviceDao mDeviceDao;

        public DeleteAllTask(DeviceDao deviceDao) {
            mDeviceDao = deviceDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDeviceDao.deleteAll();
            return null;
        }
    }
}

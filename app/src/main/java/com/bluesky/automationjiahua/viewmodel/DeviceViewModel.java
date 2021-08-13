package com.bluesky.automationjiahua.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bluesky.automationjiahua.database.Device;
import com.bluesky.automationjiahua.database.DeviceRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:
 */
public class DeviceViewModel extends AndroidViewModel {
    private LiveData<List<Device>> mLiveDataDevices;
    private DeviceRepository mDeviceRepository;

    public DeviceViewModel(@NonNull @NotNull Application application) {
        super(application);
        mDeviceRepository = new DeviceRepository(application);
        mLiveDataDevices = mDeviceRepository.getAllDevices();

    }


    public LiveData<List<Device>> getLiveDataDevices() {
        return mLiveDataDevices;
    }

    public LiveData<List<Device>> findDevicesWithPattern(String domain, String column, String keyWords) {
        return mDeviceRepository.findDeviceByPattern(domain, column, keyWords);
    }

    public LiveData<List<Device>> findDevicesWithPatternByTag(String pattern) {
        return mDeviceRepository.findDeviceByTag(pattern);
    }

    public LiveData<List<Device>> findDevicesWithPatternByAffect(String pattern) {
        return mDeviceRepository.findDeviceByAffect(pattern);
    }

    public LiveData<List<Device>> findDevicesWithPatternByName(String pattern) {
        return mDeviceRepository.findDeviceByName(pattern);
    }

    public LiveData<List<Device>> findDevicesWithPatternByStandard(String pattern) {
        return mDeviceRepository.findDeviceByStandard(pattern);
    }

    public LiveData<List<Device>> findDevicesWithPatternByType(String pattern) {
        return mDeviceRepository.findDeviceByType(pattern);
    }
}

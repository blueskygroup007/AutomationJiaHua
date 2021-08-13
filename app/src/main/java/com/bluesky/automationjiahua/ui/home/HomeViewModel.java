package com.bluesky.automationjiahua.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    //todo 每次返回fragment都会调用oncreate方法的解决
    //在fragment的viewmodel中保存列表数据和当前位置信息等。
    //todo 可能:不在viewmodel中保存本页面所需的数据,那么viewmodel,或者livedata,就会失效.那么就会重新create.甚至整个app重新启动
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
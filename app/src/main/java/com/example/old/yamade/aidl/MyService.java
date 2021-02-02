package com.example.old.yamade.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;

public class MyService extends Service {


    IBinder mBinder = new ProcessManager.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getPid() throws RemoteException {
            return ProcessUtil.getCurProcessName(MyService.this) ;
        }
    };

    /**
     * 1、服务端注册Binder
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}

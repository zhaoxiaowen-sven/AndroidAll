package com.sven.androidall.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class RemoteService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private IBinder binder = new IRemoteService.Stub() {
        @Override
        public String getPersonName(String p) throws RemoteException {
            return null;
        }
    };


}

package com.pr.f2w;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


public class f2w extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            if (com.google.firebase.FirebaseApp.getApps(this).isEmpty())
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }




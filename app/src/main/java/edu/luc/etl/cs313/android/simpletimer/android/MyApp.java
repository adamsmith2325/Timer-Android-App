package edu.luc.etl.cs313.android.simpletimer.android;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application {

    private static Context AppContext;

    @Override
    public void onCreate(){
        super.onCreate();
        MyApp.AppContext = getApplicationContext();
    }

    public static Context getAppContext(){return MyApp.AppContext;}

}

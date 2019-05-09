package com.example.beyond.manager;

import com.example.beyond.model.DBHelper;
import com.example.beyond.model.HttpHelper;
import com.example.beyond.model.PreferencesHelper;



public class DataManager implements HttpHelper, DBHelper, PreferencesHelper{

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }


}

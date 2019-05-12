package com.dev.common.manager;

import com.dev.common.model.DBHelper;
import com.dev.common.model.HttpHelper;
import com.dev.common.model.PreferencesHelper;



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

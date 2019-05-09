package com.example.beyond.constant;

import android.content.Context;

import java.io.File;

/**
 * 作者：ly-xuxiaolong
 * 版本：1.0
 * 创建日期：2019/5/9
 * 描述：
 * 修订历史：
 */
public class Config{
    public static final String CACHE = "/NetCache";


    public static String getDataPath(Context context){
        return context.getCacheDir().getAbsolutePath() + File.separator + "data";
    }

    public static String getCachePath(Context context){
        return getDataPath(context)+CACHE;
    }
}

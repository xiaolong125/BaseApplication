package com.dev.flygo.demo;

import java.util.ArrayList;

/**
 * 作者：ly-xuxiaolong
 * 版本：1.0
 * 创建日期：2019/5/13
 * 描述：
 * 修订历史：
 */
public class AllCity{
    private String error_code;
    private String reason;
    private String resultcode;
    private ArrayList<City> result;

    public String getError_code(){
        return error_code;
    }

    public void setError_code(String error_code){
        this.error_code = error_code;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public String getResultcode(){
        return resultcode;
    }

    public void setResultcode(String resultcode){
        this.resultcode = resultcode;
    }

    public ArrayList<City> getResult(){
        return result;
    }

    public void setResult(ArrayList<City> result){
        this.result = result;
    }
}

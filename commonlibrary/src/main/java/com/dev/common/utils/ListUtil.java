package com.dev.common.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil{
    //List去重
    public static <T> List<T> removeDuplicate(List<T> list){
        List<T> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if(newList.contains(list.get(i))){
                //将之前的删掉，添加新的，保持顺序
                newList.remove(list.get(i));
                newList.add(list.get(i));
            }else {
                newList.add(list.get(i));
            }
        }
        return newList;
    }
}

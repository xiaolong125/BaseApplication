package com.dev.flygo.demo;

import java.util.Objects;

/**
 * 作者：ly-xuxiaolong
 * 版本：1.0
 * 创建日期：2019/5/13
 * 描述：
 * 修订历史：
 */
public class City{
    /**
     * id : 1
     * province : 北京
     * city : 北京
     * district : 北京
     */

    private String id;
    private String province;
    private String city;
    private String district;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getProvince(){
        return province;
    }

    public void setProvince(String province){
        this.province = province;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getDistrict(){
        return district;
    }

    public void setDistrict(String district){
        this.district = district;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        City city1 = (City) o;
        return Objects.equals(id, city1.id) && Objects.equals(province, city1.province) && Objects.equals(city, city1.city) && Objects.equals(district, city1.district);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, province, city, district);
    }

    @Override
    public String toString(){
        return "City{" + "id='" + id + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + ", district='" + district + '\'' + '}';
    }
}

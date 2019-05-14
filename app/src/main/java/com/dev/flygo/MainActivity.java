package com.dev.flygo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.dev.flygo.demo.AllCity;
import com.dev.flygo.demo.Api;
import com.dev.flygo.demo.City;
import com.dev.flygo.demo.GsonUtil;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getAllCity(){
        Retrofit retrofit = createRetrofit();
        Api api = retrofit.create(Api.class);
        Observable<AllCity> observable = api.getAllCity("");
        observable.subscribeOn(Schedulers.io()).
                flatMap(new Function<AllCity, ObservableSource<City>>(){
                    @Override
                    public ObservableSource<City> apply(AllCity allCity) throws Exception{
                        ArrayList<City> result = allCity.getResult();
                        return Observable.fromIterable(result);
                    }
                }).filter(new Predicate<City>(){
                    @Override
                    public boolean test(City city) throws Exception{
                        if(Integer.parseInt(city.getId()) < 5){
                            return true;
                        }
                        return false;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<City>(){
                    @Override
                    public void accept(City city) throws Exception{
                        Log.d("消费事件", city.toString());
                    }
                });
    }

    private void getAllProvinces(){
        Retrofit retrofit = createRetrofit();
        Api api = retrofit.create(Api.class);
        api.getProvinces("").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).onBackpressureLatest().subscribeWith(new Subscriber<AllCity>(){
            @Override
            public void onSubscribe(Subscription s){
            }

            @Override
            public void onNext(AllCity allCity){
            }

            @Override
            public void onError(Throwable t){
            }

            @Override
            public void onComplete(){
            }
        });
    }

    private Retrofit createRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return new Retrofit.Builder().baseUrl(baseUrl).client(builder.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    private void test1(){
//        Map<Integer,List<City>> map = new HashMap<>();
//        List<City> list1 = new ArrayList<>();
//        City city1 = new City("1");
//        City city2 = new City("2");
//        list1.add(city1);
//        list1.add(city2);
//        map.put(1,list1);
//
//        List<City> list2 = new ArrayList<>();
//        City city3 = new City("3");
//        City city4 = new City("4");
//        list2.add(city3);
//        list2.add(city4);
//        map.put(2,list2);


    }


    public void test1(View view){
        Map<String,City> map1 = new HashMap<>();
        City city = new City("1");
        map1.put("1",city);
        List<Map<String,City>> list1 = new ArrayList<>();
        list1.add(map1);
//        String save = GsonUtils.saveListMap(list1);
//        List<Map<String, Object>> maps = GsonUtils.GsonToListMaps(save);

    }

    public void test2(View view){
        Map<String,List<City>> map1 = new HashMap<>();
        City city1 = new City("1","湖北","武汉","省会");
        City city2 = new City("2","广东","深圳","特区");
        List<City> list1 = new ArrayList<>();
        list1.add(city1);
        list1.add(city2);
        map1.put("1",list1);
        City city3 = new City("3","四川","成都","省会");
        City city4 = new City("4","湖南","长沙","省会");
        List<City> list2 = new ArrayList<>();
        list2.add(city3);
        list2.add(city4);
        map1.put("2",list2);
        String save = GsonUtil.saveListMap(map1);
//         String save = GsonUtils.putListMap(map1);
        SPUtils.getInstance().put("test2",save);

        String test2 = SPUtils.getInstance().getString("test2");
        Type mapType = GsonUtil.getMapType(new TypeToken<String>(){}.getType(), new TypeToken<List<City>>(){}.getType());
        Log.e("1111",mapType.toString());
        Type type = new TypeToken<Map<String, List<City>>>(){}.getType();
        Log.e("2222",type.toString());
        LinkedTreeMap<String, List<City>> linkedTreeMap = GsonUtil.getListMap(test2,type);

        Log.e("---",linkedTreeMap.toString());
//        Map<String, List<City>> listMap = GsonUtils.getListMap(test2);

        //        LinkedTreeMap<String, List<City>> stringListMap =
        List<City> list = linkedTreeMap.get("1");
        Log.e("---",list.get(0).toString());
        Log.e("======",list.toString());

    }
}

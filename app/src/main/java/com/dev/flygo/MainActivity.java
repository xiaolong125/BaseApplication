package com.dev.flygo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dev.flygo.demo.AllCity;
import com.dev.flygo.demo.Api;
import com.dev.flygo.demo.City;

import java.util.ArrayList;

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
        Retrofit retrofit = createRetrofit();
        Api api = retrofit.create(Api.class);
        Observable<AllCity> observable = api.getAllCity(baseUrl);
        observable.subscribeOn(Schedulers.io()).flatMap(new Function<AllCity, ObservableSource<City>>(){
            @Override
            public ObservableSource<City> apply(AllCity city){
                ArrayList<City> result = city.getResult();
                return Observable.fromIterable(result);
            }
        }).filter(new Predicate<City>(){
            @Override
            public boolean test(City city){
                String id = city.getId();
                if(Integer.parseInt(id) < 5){
                    return true;
                }
                return false;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<City>(){
            @Override
            public void accept(City city){
                System.out.println(city);
            }
        });
    }

    private Retrofit createRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return new Retrofit.Builder().baseUrl(baseUrl).client(builder.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }
}

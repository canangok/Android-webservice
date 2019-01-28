package com.example.canan.webservice.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    GetUserDataService service;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClientFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
    private static OkHttpClient getOkHttpClientFactory(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return builder.build();
    }
    public GetUserDataService getService() {
        service = getRetrofitInstance().create(GetUserDataService.class);
        return service;
    }
}

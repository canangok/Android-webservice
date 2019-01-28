package com.example.canan.webservice.network;

import com.example.canan.webservice.model.UserModel;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetUserDataService {

    @GET("posts")
    Observable<List<UserModel>> getUserData();

}

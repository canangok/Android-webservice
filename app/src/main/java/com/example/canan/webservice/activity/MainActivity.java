package com.example.canan.webservice.activity;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.canan.webservice.R;
import com.example.canan.webservice.adapter.UserAdapter;
import com.example.canan.webservice.model.UserModel;
import com.example.canan.webservice.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    List<UserModel> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserList();
    }
    void getUserList() {

        new RetrofitInstance().getService().getUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<UserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(List<UserModel> userModels) {
                        userList=userModels;
            }

            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
                    if(userList.size()>0){
                        generateUserList(userList);
                    }
            }
        });
    }
    void generateUserList(List<UserModel> userDataList){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_user_list);
        UserAdapter userAdapter = new UserAdapter(userDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);
    }
}
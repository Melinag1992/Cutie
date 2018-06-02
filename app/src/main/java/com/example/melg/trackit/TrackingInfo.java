package com.example.melg.trackit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.melg.trackit.backend.TrackingServices;
import com.example.melg.trackit.model.TrackingModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by melg on 6/2/18.
 */

public class TrackingInfo extends AppCompatActivity{
    private TextView trackinginfo;
    private  Retrofit retrofit;
    private String tracking_number_entered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_tracking_activity);
        

        tracking_number_entered = getIntent().getExtras().getString("tracking_number").toString();
        init();
        
    }

    private void init() {
       trackinginfo =  findViewById(R.id.info_shown);

    }


    public void getTrackinginfo(){


        retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.aftership.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        TrackingServices trackingServices = retrofit.create(TrackingServices.class);
        trackingServices.getInfo(tracking_number_entered).enqueue(new Callback<TrackingModel>() {
            @Override
            public void onResponse(Call<TrackingModel> call, Response<TrackingModel> response) {

                if(response.isSuccessful()){
                    Toast.makeText(TrackingInfo.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrackingModel> call, Throwable t) {

                Toast.makeText(TrackingInfo.this, "NOT SUCCESSFUL", Toast.LENGTH_SHORT).show();

            }
        });

    }


}

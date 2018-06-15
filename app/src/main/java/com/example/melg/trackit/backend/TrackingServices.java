package com.example.melg.trackit.backend;

import com.example.melg.trackit.model.TrackingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by melg on 6/2/18.
 */

public interface TrackingServices {

    @GET("trackings")
    Call<TrackingModel> getAll();

}

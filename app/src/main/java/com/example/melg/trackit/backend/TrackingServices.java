package com.example.melg.trackit.backend;

import com.example.melg.trackit.model.TrackingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by melg on 6/2/18.
 */

public interface TrackingServices {
    @Headers({
            "aftership-api-key: b62cb87b-7d13-4bfe-99bb-cb05577528b8",
            "Content-Type: application/json"
    })
    @GET("/trackings/:slug/:{tracking_number}")
    Call<TrackingModel> getInfo(@Path("tracking_number") String tracking_number);

}

package com.nhom03.hust.quanlydonhang.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.rest.ApiInterface;

/**
 * Created by sakura on 01/12/2016.
 */

public class DanhSachKhachHang extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        ApiInterface apiService =
//                ApiClient.getClient().create(ApiInterface.class);
//
//        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
//        call.enqueue(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
//                List<Movie> movies = response.body().getResults();
//                Log.d(TAG, "Number of movies received: " + movies.size());
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse>call, Throwable t) {
//                // Log error here since request failed
//                Log.e(TAG, t.toString());
//            }
//        });
    }

}

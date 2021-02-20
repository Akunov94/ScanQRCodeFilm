package com.example.scanqrcodefilm.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.scanqrcodefilm.data.model.Film;
import com.example.scanqrcodefilm.data.remote.RetrofitFactory;
import com.example.scanqrcodefilm.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        ui.btnDecodeQr.setOnClickListener(view -> {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.initiateScan();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result.getContents() != null && data != null) {
            String decodeFilmId = result.getContents();
            ShowFilmInfo(decodeFilmId);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ShowFilmInfo(String id) {
        RetrofitFactory.getInstance().getFilmId(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Intent intent = new Intent(MainActivity.this, FilmInfo.class);
                        intent.putExtra("film_Id", response.body().getId());
                        intent.putExtra("film_title", response.body().getTitle());
                        intent.putExtra("film_des", response.body().getDescription());
                        startActivity(intent);
                        Log.i("f_Id", response.body().getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });

    }
}
package com.example.scanqrcodefilm.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.scanqrcodefilm.R;
import com.example.scanqrcodefilm.databinding.ActivityFilmInfoBinding;

public class FilmInfo extends AppCompatActivity {
    private ActivityFilmInfoBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityFilmInfoBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        setElement();
    }

    private void setElement() {
        Intent intent = getIntent();
        if (intent != null) {
            String film_id = intent.getStringExtra("film_Id");
            String film_title = intent.getStringExtra("film_title");
            String film_des = intent.getStringExtra("film_des");
            ui.FilmId.setText("id filma \n"+film_id);
            ui.FilmTitle.setText(film_title);
            ui.FilmDescription.setText(film_des);
        }
    }
}
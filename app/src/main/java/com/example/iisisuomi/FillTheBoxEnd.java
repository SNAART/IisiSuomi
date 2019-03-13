package com.example.iisisuomi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iisisuomi.R;

public class FillTheBoxEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_choice_statistic);
    }

    public void onRepeatClick(View view){
        Intent intent = new Intent(FillTheBoxEnd.this, FillTheBoxIntro.class);
        startActivity(intent);
    }

    public void onExitClick(View view){
        Intent intent = new Intent(FillTheBoxEnd.this, MainActivity.class);
        startActivity(intent);
    }
}
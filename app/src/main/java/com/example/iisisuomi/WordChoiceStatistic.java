package com.example.iisisuomi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.iisisuomi.R;

public class WordChoiceStatistic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_choice_statistic);

        TextView txtScore = findViewById(R.id.score);
        TextView txtHighScore = findViewById(R.id.highscore);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        txtScore.setText("Your score: " + score);

        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore", 0);
        if (highscore >= score)
            txtHighScore.setText("High score: " + highscore);
        else {
            txtHighScore.setText("New high score: " + score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }
    }

    public void onRepeatClick(View view){
        Intent intent = new Intent(WordChoiceStatistic.this, WordChoiceIntro.class);
        startActivity(intent);
    }

    public void onExitClick(View view){
        Intent intent = new Intent(WordChoiceStatistic.this, MainActivity.class);
        startActivity(intent);
    }
}
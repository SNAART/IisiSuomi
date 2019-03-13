package com.example.iisisuomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.iisisuomi.R;

public class WordChoiceIntro extends AppCompatActivity {
    private App app;
    private Button button_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_choice_intro);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        app = new App(this);
        button_start = (Button) findViewById(R.id.button_Start_game2);

    }

    public void StartGame2OnClick(View view){
        Bundle game_data_extra = new Bundle();
        game_data_extra.putInt("Game",2);
        Intent ChapterSelect_Intent = new Intent(WordChoiceIntro.this,SelectChapter2.class);
        ChapterSelect_Intent.putExtras(game_data_extra);
        WordChoiceIntro.this.startActivity(ChapterSelect_Intent);
    }
}


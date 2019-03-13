package com.example.iisisuomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.iisisuomi.R;

public class SelectGame extends AppCompatActivity {
    private App app;
    private Button button_fillthegaps,button_timechallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_game);
        app = new App(this);
        button_fillthegaps = (Button) findViewById(R.id.button_fillthebox);
        button_timechallenge = (Button) findViewById(R.id.button_wordchoice);
    }

    public void FillTheBoxOnClick(View view){
        Bundle game_data_extra = new Bundle();
        game_data_extra.putInt("Game",1);
        Intent ChapterSelect_Intent = new Intent(SelectGame.this,FillTheBoxIntro.class);
        ChapterSelect_Intent.putExtras(game_data_extra);
        SelectGame.this.startActivity(ChapterSelect_Intent);
    }

    public void WordChoiceOnClick(View view){
        Bundle game_data_extra = new Bundle();
        game_data_extra.putInt("Game",2);
        Intent ChapterSelect_Intent = new Intent(SelectGame.this,WordChoiceIntro.class);
        ChapterSelect_Intent.putExtras(game_data_extra);
        SelectGame.this.startActivity(ChapterSelect_Intent);
    }
}

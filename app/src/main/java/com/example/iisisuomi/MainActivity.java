package com.example.iisisuomi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.iisisuomi.R;

public class MainActivity extends AppCompatActivity {
    private App app;
    private Button button_vocab,button_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        button_vocab = (Button) findViewById(R.id.button_vocab);
        button_game = (Button) findViewById(R.id.button_game);
    }

    public void VocabOnClick(View view){
        Intent Vocab_Intent = new Intent(MainActivity.this, SelectChapter.class);
        MainActivity.this.startActivity(Vocab_Intent);
    }

    public void GameOnClick(View view){
        Intent Vocab_Intent = new Intent(MainActivity.this, SelectGame.class);
        MainActivity.this.startActivity(Vocab_Intent);
    }
}

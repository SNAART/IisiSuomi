package com.example.iisisuomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.iisisuomi.R;

import java.util.ArrayList;
import java.util.List;

public class Vocabulary extends AppCompatActivity {
    private App app;
    private Button button_next,button_pre,button_des;
    private TextView word, des, count_text;
    private List<Word> usedWord;
    private int totalWord;
    private int count;
    private boolean press;
    private List<String> chapterChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vocabulary);
        app = new App(this);
        word = (TextView) findViewById(R.id.word);
        des = (TextView) findViewById(R.id.des);
        count_text = (TextView) findViewById(R.id.text_count);
        button_des = (Button) findViewById(R.id.button_vocab_des);
        button_pre = (Button) findViewById(R.id.button_vocab_pre);
        button_next = (Button) findViewById(R.id.button_vocab_next);

        usedWord = new ArrayList<>();
        totalWord = 0;
        count = 0;
        press=false;

        Intent ChapterSelect_Intent = getIntent();
        Bundle ChapterSelect_Bundle = ChapterSelect_Intent.getExtras();

        chapterChoice = new ArrayList<>();
        chapterChoice = ChapterSelect_Bundle.getStringArrayList("Chapters");
        usedWord = new ArrayList<>();
        for(int i = 0; i < chapterChoice.size(); i++) {
            totalWord += app.getChapter(chapterChoice.get(i)).getWordList().size();
        }
    }

    public void PreOnClick(View view){
        if (count <= 1) {
            if (press==false) des.setText("Press NEXT to start");
            else des.setText("First word reached!");
            return;
        }
        count--;
        word.setText(usedWord.get(count - 1).getWord());
        count_text.setText(count + " out of " + totalWord);
        des.setText("Try to guess");
    }

    public void DesOnClick(View view){
        Word ranWord;
        ranWord = app.getWord(word.getText().toString());
        if (ranWord == null) {
            ranWord = new Word("no name", "Press NEXT to start", new Chapter("201"));
        }
        des.setText(ranWord.getDescription());
    }


    public void NextOnClick(View view){
        press=true;
        if (count < usedWord.size()) {
            word.setText(usedWord.get(count).getWord());
            count++;
            count_text.setText(count + " out of " + totalWord);
            des.setText("Try to guess");
            return;
        }
        while(count <= totalWord) {
            Word ranWord;
            if (count == totalWord) {
                des.setText("Last word reached!");
                break;
            }
            ranWord = app.getRandomWordBaseOnChapter(chapterChoice);
            if (!(usedWord.contains(ranWord))) {
                word.setText(ranWord.getWord());
                usedWord.add(ranWord);
                count++;
                count_text.setText(count + " out of " + totalWord);
                des.setText("Try to guess");
                break;
            }
        }
    }
}

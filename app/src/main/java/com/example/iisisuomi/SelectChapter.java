package com.example.iisisuomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.iisisuomi.R;

import java.util.ArrayList;
import java.util.List;

public class SelectChapter extends AppCompatActivity {
    private App app;
    private CheckBox chapter1, chapter2, chapter3, chapter4, chapter5;
    private Button button_start;
    private int totalWord,count;
    private List<String> chapterChoice;
    private boolean ticked;
    private TextView headtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_chapter);
        app = new App(this);
        chapter1 = (CheckBox) findViewById(R.id.checkbox_chaper1);
        chapter2 = (CheckBox) findViewById(R.id.checkbox_chaper2);
        chapter3 = (CheckBox) findViewById(R.id.checkbox_chaper3);
        chapter4 = (CheckBox) findViewById(R.id.checkbox_chaper4);
        chapter5 = (CheckBox) findViewById(R.id.checkbox_chaper5);
        button_start = (Button) findViewById(R.id.button_selectchapter_start);
        headtitle = (TextView) findViewById(R.id.headtitle);
        totalWord = 0;
        count = 0;
    }

    public void SelectChapter_StartOnClick(View view){
        List<String> chapters = new ArrayList<>();
        ticked=false;
        if (chapter1.isChecked()) {
            chapters.add("1");
            ticked=true;
        }
        if (chapter2.isChecked()) {
            chapters.add("2");
            ticked=true;
        }
        if (chapter3.isChecked()) {
            chapters.add("3");
            ticked=true;
        }
        if (chapter4.isChecked()) {
            chapters.add("4");
            ticked=true;
        }
        if (chapter5.isChecked()) {
            chapters.add("5");
            ticked=true;
        }
        if (ticked==false){
            headtitle.setText("AT LEAST ONE");
            return;
        }
        totalWord = 0;
        count = 0;
        for(int i = 0; i < chapters.size(); i++) {
            totalWord += app.getChapter(chapters.get(i)).getWordList().size();
        }
        chapterChoice = chapters;
        Bundle chapter_data_extra = new Bundle();
        chapter_data_extra.putStringArrayList("Chapters",(ArrayList<String>)chapters);
        Intent ChapterSelect_Intent = new Intent(SelectChapter.this,Vocabulary.class);
        ChapterSelect_Intent.putExtras(chapter_data_extra);
        SelectChapter.this.startActivity(ChapterSelect_Intent);
        headtitle.setText("SELECT CHAPTER");
    }
}

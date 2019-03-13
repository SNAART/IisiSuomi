package com.example.iisisuomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iisisuomi.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordChoice extends AppCompatActivity {
    private App app;
    private Button button_quit, button_choice1,button_choice2,button_choice3;
    private TextView QuestionView;
    private int QuestionNumber,Score,count;
    private TextView ScoreView;
    private List<String> chapterChoice;
    private ArrayList<Word> wordList;
    private String mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_choice);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        app = new App(this);
        button_quit = (Button) findViewById(R.id.buttonwordChoiceQuit);
        QuestionView = findViewById(R.id.WordChoiceQuestion);
        button_choice1 = findViewById(R.id.WordChoiceChoice1);
        button_choice2 = findViewById(R.id.WordChoiceChoice2);
        button_choice3 = findViewById(R.id.WordChoiceChoice3);
        ScoreView = findViewById(R.id.WordChoiceScore);
        QuestionNumber = 1;
        Score = 0;
        count=0;

        Intent ChapterSelect_Intent = getIntent();
        Bundle ChapterSelect_Bundle = ChapterSelect_Intent.getExtras();
        chapterChoice = new ArrayList<>();
        chapterChoice = ChapterSelect_Bundle.getStringArrayList("Chapters");
        Word ranWord;
        wordList = new ArrayList<>();
        while (count<20){
            ranWord = app.getRandomWordBaseOnChapter(chapterChoice);
            if (!(wordList.contains(ranWord))) {
                wordList.add(ranWord);
                count++;
            }
        }

        updateQuestion();
        updateScore(Score);

        button_choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button_choice1.getText() == mAnswer) {
                    Score++;
                    updateScore(Score);
                    updateQuestion();
                    Toast.makeText(WordChoice.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WordChoice.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        button_choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button_choice2.getText() == mAnswer) {
                    Score++;
                    updateScore(Score);
                    updateQuestion();
                    Toast.makeText(WordChoice.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WordChoice.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        button_choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button_choice3.getText() == mAnswer) {
                    Score++;
                    updateScore(Score);
                    updateQuestion();
                    Toast.makeText(WordChoice.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WordChoice.this, "Wrong!", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });
    }

    private void updateQuestion() {
        if (QuestionNumber < 20) {
            QuestionView.setText(wordList.get(QuestionNumber).getWord());
            List<String> AnswerChoices = new ArrayList<>();
            AnswerChoices.add(wordList.get(QuestionNumber).getDescription());
            Word ranWord;
            ranWord = app.getRandomWordBaseOnChapter(chapterChoice);
            if (!(AnswerChoices.contains(ranWord)))
                AnswerChoices.add(ranWord.getDescription());

            ranWord = app.getRandomWordBaseOnChapter(chapterChoice);
            if (!(AnswerChoices.contains(ranWord)))
                AnswerChoices.add(ranWord.getDescription());

            Collections.shuffle(AnswerChoices);

            button_choice1.setText(AnswerChoices.get(0));
            button_choice2.setText(AnswerChoices.get(1));
            button_choice3.setText(AnswerChoices.get(2));
            mAnswer = wordList.get(QuestionNumber).getDescription();
            QuestionNumber++;
        }
        else {
            Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WordChoiceStatistic.class);
            intent.putExtra("score", Score);
            startActivity(intent);
        }
    }

    private void updateScore(int point) {

        ScoreView.setText("Score: " + point);
    }

    public void quitOnClick(View view){
        Intent intent = new Intent(this, SelectGame.class);
        startActivity(intent);
    }
}

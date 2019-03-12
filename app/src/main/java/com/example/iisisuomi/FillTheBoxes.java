package com.example.iisisuomi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iisisuomi.Adapter.GridViewAnswerAdapter;
import com.example.iisisuomi.Adapter.GridViewQuestionAdapter;
import com.example.iisisuomi.Common.Common;
import com.example.iisisuomi.Common.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FillTheBoxes extends AppCompatActivity {

    public List<String> suggestSource = new ArrayList<>();

    public GridViewAnswerAdapter answerAdapter;
    public GridViewQuestionAdapter questionAdapter;
    public TextView engWord;
    public Button btnSubmit;

    public GridView gridViewAnswer, gridViewQuestion;

    private ArrayList<Word> word_list;

    public char[] answer;

    String correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word_list = new ArrayList<>();
        word_list.add(new Word("päivä","day"));
        word_list.add(new Word("opiskelija","student"));
        initView();
    }

    private void initView() {
        gridViewAnswer = (GridView)findViewById(R.id.gridViewAnswer);
        gridViewQuestion = (GridView)findViewById(R.id.gridViewQuestion);
        engWord = (TextView)findViewById(R.id.engWord);

        setupList();
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="";
                for(int i=0;i< Common.user_submit_answer.length;i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                if(result.equals(correct_answer))
                {
                    Toast.makeText(getApplicationContext(),"Finish ! This is "+result,Toast.LENGTH_SHORT).show();

                    //Reset
                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //Set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();

                    GridViewQuestionAdapter questionAdapter = new GridViewQuestionAdapter(suggestSource,getApplicationContext(), FillTheBoxes.this);
                    gridViewQuestion.setAdapter(questionAdapter);
                    questionAdapter.notifyDataSetChanged();

                    setupList();
                }
                else
                {
                    Toast.makeText(FillTheBoxes.this, "Incorrect!!! Try to finish the word!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void setupList() {
        //random word
        Random random = new Random();
        int wordSelected = random.nextInt(word_list.size());
        correct_answer = word_list.get(wordSelected).getFin();
        answer = correct_answer.toCharArray();
        engWord.setText(word_list.get(wordSelected).getEng().toUpperCase());
        Common.user_submit_answer = new char[answer.length];

        //add answer character to List
        suggestSource.clear();
        for(char item:answer) {
            //add finnish word to list
            suggestSource.add(String.valueOf(item));
        }

        //add random characters
        for(int i = answer.length; i<answer.length*3; i++){
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);
        }
        //alphabet
        for(int i = 0; i<Common.alphabet_character.length; i++){
            suggestSource.add(Common.alphabet_character[i]);
        }

        //sorting randomly
        Collections.shuffle(suggestSource);

        //set for GridView
        answerAdapter = new GridViewAnswerAdapter(setupNullList(),this);
        questionAdapter = new GridViewQuestionAdapter(suggestSource,this,this);

        answerAdapter.notifyDataSetChanged();
        questionAdapter.notifyDataSetChanged();

        gridViewQuestion.setAdapter(questionAdapter);
        gridViewAnswer.setAdapter(answerAdapter);
    }
    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for(int i=0; i<answer.length; i++) {
            result[i]=' ';
        }
        return result;
    }
}
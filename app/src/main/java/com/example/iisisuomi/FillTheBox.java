package com.example.iisisuomi;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.GridView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.iisisuomi.Adapter.GridViewAnswerAdapter;
        import com.example.iisisuomi.Adapter.GridViewQuestionAdapter;
        import com.example.iisisuomi.R;

        import java.util.ArrayList;
        import java.util.List;

public class FillTheBox extends AppCompatActivity {

    private App app;
    public List<String> suggestSource = new ArrayList<>();
    public GridViewAnswerAdapter answerAdapter;
    public GridViewQuestionAdapter questionAdapter;
    public TextView engWord;
    public Button btnSubmit;
    public GridView gridViewAnswer, gridViewQuestion;
    private ArrayList<Word> word_list;
    public char[] answer;
    private TextView QuestionView;
    private int totalWord;
    private int count;
    private int numQuestion;
    private List<String> chapterChoice;
    String correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_the_box);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        app = new App(this);
        Intent ChapterSelect_Intent = getIntent();
        Bundle ChapterSelect_Bundle = ChapterSelect_Intent.getExtras();
        chapterChoice = new ArrayList<>();
        chapterChoice = ChapterSelect_Bundle.getStringArrayList("Chapters");
        QuestionView = findViewById(R.id.FillTheBoxQuestion);
        word_list = new ArrayList<>();;
        totalWord = 15;
        count = 0;
        numQuestion=0;
        Word ranWord;
        while(count<totalWord){
            ranWord = app.getRandomWordBaseOnChapter(chapterChoice);
            if (!(word_list.contains(ranWord))) {
                word_list.add(ranWord);
                count++;
            }
        }
        updateQuestion(numQuestion+1);
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
                    Toast.makeText(getApplicationContext(),"Finish ! This is "+result.toUpperCase(),Toast.LENGTH_SHORT).show();
                    numQuestion++;
                    updateQuestion(numQuestion+1);
                    //Reset
                    Common.count = 0;
                    Common.user_submit_answer = new char[correct_answer.length()];

                    //Set Adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();
                    GridViewQuestionAdapter questionAdapter = new GridViewQuestionAdapter(suggestSource,getApplicationContext(), FillTheBox.this);
                    gridViewQuestion.setAdapter(questionAdapter);
                    questionAdapter.notifyDataSetChanged();

                    setupList();
                    if(numQuestion == 15){
                        Toast.makeText(FillTheBox.this, "That was the last question!", Toast.LENGTH_SHORT).show();
                        Intent Statistic_Intent = new Intent(FillTheBox.this, FillTheBoxEnd.class);
                        FillTheBox.this.startActivity(Statistic_Intent);
                    }
                }
                else
                {
                    Toast.makeText(FillTheBox.this, "Incorrect!!! Try to finish the word!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void setupList() {
        int wordSelected = numQuestion;
        correct_answer = word_list.get(wordSelected).getWord();
        answer = correct_answer.toCharArray();
        engWord.setText(word_list.get(wordSelected).getDescription().toUpperCase());
        Common.user_submit_answer = new char[answer.length];

        //add answer character to List
        suggestSource.clear();

        //alphabet
        for(int i = 0; i<Common.alphabet_character.length; i++){
            suggestSource.add(Common.alphabet_character[i]);
        }

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

    private void updateQuestion(int count) {

        QuestionView.setText("#" + count+ "/15");
    }

    public void quitOnClick(View view){
        Intent intent = new Intent(this, SelectGame.class);
        startActivity(intent);
    }
}

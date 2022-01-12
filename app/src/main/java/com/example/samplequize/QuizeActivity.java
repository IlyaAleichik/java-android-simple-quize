package com.example.samplequize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.samplequize.fragments.MyAdapter;
import com.example.samplequize.helpers.QuizDbHelper;
import com.example.samplequize.helpers.Single;
import com.example.samplequize.models.Leaderboard;
import com.example.samplequize.models.Question;
import java.util.Collections;
import java.util.List;

public class QuizeActivity extends AppCompatActivity {
    private List<Question> questionList;
    private Button buttonConfirmNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize);
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        Collections.shuffle(questionList);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        ViewPager2 pager = findViewById(R.id.view_pager2);
        MyAdapter adapter = new MyAdapter(this,questionList);
        pager.setAdapter(adapter);
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (pager.getCurrentItem() + 1 < adapter.getItemCount()){
                    pager.setCurrentItem(pager.getCurrentItem()+1);
                    if (pager.getCurrentItem() == adapter.getItemCount() - 1){buttonConfirmNext.setText("Finish");}
                }else {
                    Leaderboard leaderboard = new Leaderboard(Single.getInstance().name, Single.getInstance().score);
                    dbHelper.customAddingLeadBoard(leaderboard);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }
}
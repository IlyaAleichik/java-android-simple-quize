package com.example.samplequize;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.samplequize.helpers.QuizContract;
import com.example.samplequize.helpers.QuizDbHelper;
import com.example.samplequize.helpers.Single;
import com.example.samplequize.models.Leaderboard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView userList;
    EditText editTextTextPersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList = findViewById(R.id.list);


        QuizDbHelper dbHelper = new QuizDbHelper(this);
        //получаем данные из бд в виде курсора
        Cursor userCursor =  dbHelper.getAllLeader();
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {QuizContract.LeaderboardTable.COLUMN_NAME, QuizContract.LeaderboardTable.COLUMN_SCORE};
        // создаем адаптер, передаем в него курсор
        SimpleCursorAdapter userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        userList.setAdapter(userAdapter);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Single.getInstance().name = editTextTextPersonName.getText().toString();
                Single.getInstance().score = 0;
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, QuizeActivity.class);
        startActivity(intent);
    }
}
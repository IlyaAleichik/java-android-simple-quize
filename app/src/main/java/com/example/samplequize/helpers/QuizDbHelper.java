package com.example.samplequize.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.samplequize.helpers.QuizContract.*;
import com.example.samplequize.models.Leaderboard;
import com.example.samplequize.models.Question;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MySampleQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER," +
                QuestionsTable.COLUMN_ANSWER_MU + " BOOLEAN" +
                ")";

        final String SQL_CREATE_LEADERBOARD_TABLE = "CREATE TABLE " +
                LeaderboardTable.TABLE_NAME + " ( " +
                LeaderboardTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LeaderboardTable.COLUMN_NAME + " TEXT, " +
                LeaderboardTable.COLUMN_SCORE + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_LEADERBOARD_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LeaderboardTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("A is correct", "A", "B", "C", "1", 1);
        addQuestion(q1);
        Question q2 = new Question("B is correct", "A", "B", "C", "2", 0);
        addQuestion(q2);
        Question q3 = new Question("C is correct", "A", "B", "C", "3", 1);
        addQuestion(q3);
        Question q4 = new Question("A is correct again", "A", "B", "C", "3",0);
        addQuestion(q4);
        Question q5 = new Question("B is correct again", "A", "B", "C", "1",1);
        addQuestion(q5);

        Leaderboard l1 = new Leaderboard( "Viktor",5);
        addLeaderboard(l1);
    }

    private void addLeaderboard(Leaderboard leaderboard){
        ContentValues cv = new ContentValues();
        cv.put(LeaderboardTable.COLUMN_NAME, leaderboard.getName());
        cv.put(LeaderboardTable.COLUMN_SCORE, leaderboard.getScore());
        db.insert(LeaderboardTable.TABLE_NAME, null, cv);
    }

    public void customAddingLeadBoard(Leaderboard leaderboard){
        Question q1 = new Question("A is correct", "A", "B", "C", "1", 1);
        ContentValues cv = new ContentValues();

        cv.put(LeaderboardTable.COLUMN_NAME, leaderboard.getName());
        cv.put(LeaderboardTable.COLUMN_SCORE, leaderboard.getScore());
        db.insert(LeaderboardTable.TABLE_NAME, null, cv);
    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_ANSWER_MU, question.getAnswerMu());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public Cursor getAllLeader() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + LeaderboardTable.TABLE_NAME, null);
        return c;
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setAnswerMu(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_MU)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
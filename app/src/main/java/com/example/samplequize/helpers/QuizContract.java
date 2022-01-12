package com.example.samplequize.helpers;

import android.provider.BaseColumns;

public final class QuizContract {
    private QuizContract(){}

    public  static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "questions";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_ANSWER_MU = "answer_mu";
    }

    public  static class LeaderboardTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_leaderboard";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SCORE = "score";
    }
}

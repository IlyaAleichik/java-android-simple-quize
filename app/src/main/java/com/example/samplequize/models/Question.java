package com.example.samplequize.models;

public class Question {
    private String question;
    private  String option1;
    private  String option2;
    private  String option3;
    private  String answerNr;
    private  int answerMu;

    public Question(){}

    public Question(String question, String option1, String option2, String option3, String answerNr, int answerMu) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
        this.answerMu = answerMu;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(String answerNr) {
        this.answerNr = answerNr;
    }

    public int getAnswerMu() {
        return answerMu;
    }

    public void setAnswerMu(int answerMu) {
        this.answerMu = answerMu;
    }

}

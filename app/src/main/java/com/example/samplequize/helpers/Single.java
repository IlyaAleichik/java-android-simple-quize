package com.example.samplequize.helpers;

public class Single {
    private static final Single INSTANCE = new Single();
    public String name = "";
    public int score = 0;
    private Single(){}
    public static Single getInstance(){
        return INSTANCE;
    }
}

package com.example.samplequize.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.samplequize.models.Question;
import java.util.List;

public class MyAdapter extends FragmentStateAdapter {

    private List<Question> questions;
    private int score;

    public MyAdapter(@NonNull FragmentActivity fragmentActivity, List<Question> questions) {
        super(fragmentActivity);
        this.questions = questions;
    }

    @Override
    public Fragment createFragment(int position) {
        Question question = this.questions.get(position);
        return new FirstFragment(question, this.questions.size(), score);
    }

    @Override
    public int getItemCount() {
        return this.questions.size();
    }
}

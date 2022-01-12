package com.example.samplequize.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.samplequize.R;
import com.example.samplequize.helpers.Single;
import com.example.samplequize.models.Question;

public class FirstFragment extends Fragment {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private Button buttonConfirmOption;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private View result;

    private Question currentQuestion;
    private int questionCounter = 0;
    private int questionCountTotal;
    private int score;
    private boolean answered;

    public FirstFragment() {
        // Required empty public constructor
    }

    public FirstFragment(Question question, int total, int score) {
        this.currentQuestion = question;
        this.questionCountTotal = total;
        this.score = score;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        result = (ViewGroup)inflater.inflate(R.layout.fragment_first, container, false);
        textViewQuestion = result.findViewById(R.id.text_view_question);
        rbGroup = result.findViewById(R.id.radio_group);
        rb1 = result.findViewById(R.id.radio_button1);
        rb2 = result.findViewById(R.id.radio_button2);
        rb3 = result.findViewById(R.id.radio_button3);
        buttonConfirmOption = result.findViewById(R.id.button_confirm_option);
     //   TextView textViewScore = (TextView) result.findViewById(R.id.text_view_score);

        buttonConfirmOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                        ((TextView)getActivity().findViewById(R.id.text_view_score)).setText("Score: " + Single.getInstance().score);
                    }
            }
        });
        return result;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.showInGUI(this.currentQuestion);
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = result.findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected)  + 1;
        if (answerNr == Integer.parseInt(currentQuestion.getAnswerNr())) {
            Single.getInstance().score++;
        }
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getAnswerNr()){
            case "1":
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case "2":
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case "3":
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }
    }

    private void showInGUI(Question question)  {
        textViewQuestion.setText(currentQuestion.getQuestion());
        rb1.setText(currentQuestion.getOption1());
        rb2.setText(currentQuestion.getOption2());
        rb3.setText(currentQuestion.getOption3());
    }
}
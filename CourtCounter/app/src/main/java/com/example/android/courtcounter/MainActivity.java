package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int scoreTeamA = 0, scoreTeamB = 0;

    public void plusThreeTeamA(View view) {
        scoreTeamA += 3;
        displayScoreTeamA(scoreTeamA);
    }

    public void plusTwoTeamA(View view) {
        scoreTeamA += 2;
        displayScoreTeamA(scoreTeamA);
    }

    public void freeThrowTeamA(View view) {
        scoreTeamA = 1;
        displayScoreTeamA(scoreTeamA);
    }

    private void displayScoreTeamA(int _scoreTeamA) {
        TextView tv = (TextView) findViewById(R.id.score_team_a);
        tv.setText(String.valueOf(_scoreTeamA));
    }

    public void plusThreeTeamB(View view) {
        scoreTeamB += 3;
        displayScoreTeamB(scoreTeamB);
    }

    public void plusTwoTeamB(View view) {
        scoreTeamB += 2;
        displayScoreTeamB(scoreTeamB);
    }

    public void freeThrowTeamB(View view) {
        scoreTeamB = 1;
        displayScoreTeamA(scoreTeamB);
    }

    private void displayScoreTeamB(int _scoreTeamB) {
        TextView tv = (TextView) findViewById(R.id.score_team_b);
        tv.setText(String.valueOf(_scoreTeamB));
    }

    public void resetScores(View v) {
        scoreTeamA = 0;
        displayScoreTeamA(scoreTeamA);

        scoreTeamB = 0;
        displayScoreTeamB(scoreTeamB);
    }
}

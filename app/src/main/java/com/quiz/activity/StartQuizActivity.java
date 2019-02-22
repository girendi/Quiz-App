package com.quiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.quiz.R;

public class StartQuizActivity extends AppCompatActivity {

    private Button btn_15, btn_30, btn_60, btn_next;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        Toolbar toolbar = findViewById(R.id.toolbar_start_quiz);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Start Quiz");

        btn_15 = findViewById(R.id.btn_15);
        btn_30 = findViewById(R.id.btn_30);
        btn_60 = findViewById(R.id.btn_60);
        btn_next = findViewById(R.id.btn_next);

        btn_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 900000;
                Intent goIntent = new Intent(getApplicationContext(), TimerActivity.class);
                goIntent.putExtra("time", time);
                startActivity(goIntent);
                finish();
            }
        });

        btn_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 1800000;
                Intent goIntent = new Intent(getApplicationContext(), TimerActivity.class);
                goIntent.putExtra("time", time);
                startActivity(goIntent);
                finish();
            }
        });

        btn_60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = 3600000;
                Intent goIntent = new Intent(getApplicationContext(), TimerActivity.class);
                goIntent.putExtra("time", time);
                startActivity(goIntent);
                finish();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                finish();
            }
        });

    }
}

package com.quiz.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.quiz.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_materi;
    private Button btn_quiz;
    private Button btn_score;
    private Button btn_result;
    private Button btn_logout;
    private Button btn_biografi;
    private Toolbar toolbar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };
        setContentView(R.layout.activity_main);

        initComponent();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quiz Hidrolosis Garam");

        btn_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MateriActivity.class));
//                finish();
            }
        });

        btn_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StartQuizActivity.class));
//                finish();
            }
        });

        btn_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScoreActivity.class));
//                finish();
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
//                finish();
            }
        });

        btn_biografi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BiografiActivity.class));
//                finish();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                //finish();
            }
        });
    }

    private void initComponent() {
        btn_materi = findViewById(R.id.btn_materi);
        btn_quiz = findViewById(R.id.btn_quiz);
        btn_score = findViewById(R.id.btn_score);
        btn_result = findViewById(R.id.btn_result);
        btn_logout = findViewById(R.id.btn_logout);
        btn_biografi = findViewById(R.id.btn_biografi);
        toolbar = findViewById(R.id.toolbar_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}

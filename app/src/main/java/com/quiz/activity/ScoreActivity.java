package com.quiz.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.quiz.R;
import com.quiz.data.Score;
import com.quiz.holder.ScoreAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private DatabaseReference score_ref;
    private Query query_score;
    private List<Score> scores;
    private ListView listViewScore;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Toolbar toolbar = findViewById(R.id.toolbar_score);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Score");

        scores = new ArrayList<>();
        listViewScore = findViewById(R.id.lv_score);
        progressDialog = new ProgressDialog(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        score_ref = database.getReference("Score");
        query_score = score_ref.orderByChild("point_lost");

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setTitle("Menampilkan Score");
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(true);
        query_score.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    scores.clear();

                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        Score score = ds.getValue(Score.class);
                        scores.add(score);
                    }

                    ScoreAdapter scoreAdapter = new ScoreAdapter(ScoreActivity.this, scores);
                    listViewScore.setAdapter(scoreAdapter);
                    progressDialog.dismiss();
                }else{
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}

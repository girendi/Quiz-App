package com.quiz.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.quiz.R;
import com.quiz.data.Score;
import com.quiz.holder.ResultAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private DatabaseReference score_ref;
    FirebaseAuth mAuth;
    private Query query_result;
    private List<Score> scores;
    private ListView listViewResult;
    String userId;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        setContentView(R.layout.activity_result);

        Toolbar toolbar = findViewById(R.id.toolbar_result);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Result");

        scores = new ArrayList<>();
        listViewResult = findViewById(R.id.lv_result);
        progressDialog = new ProgressDialog(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        score_ref = database.getReference("Score");
        query_result = score_ref.orderByChild("user_id").equalTo(userId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setTitle("Menampilkan Result");
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(true);
        query_result.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    scores.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        Score score = ds.getValue(Score.class);
                        scores.add(score);
                    }
                    ResultAdapter resultAdapter = new ResultAdapter(ResultActivity.this, scores);
                    listViewResult.setAdapter(resultAdapter);
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

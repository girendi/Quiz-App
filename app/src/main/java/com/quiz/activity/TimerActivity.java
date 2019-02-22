package com.quiz.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.quiz.R;
import com.quiz.data.DataSoal;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TimerActivity extends AppCompatActivity {

    private TextView txtNo, txtTimer, txtSoal, txtAnswer;
    CountDownTimer yourCountDownTimer;

    private RadioGroup rg;
    private RadioButton rb_a, rb_b, rb_c, rb_d, rb_e;
    private Button btn_lanjut, btn_check;

    int arr, no;
    int x = 0, jumlah = 0, time;
    int point = 0, point_lost = 0;
    String jawaban, nomor, userId, userName, dateTime;

    String textBenar = "Jawaban Anda Benar";
    String textSalah = "Jawaban Kurang Tepat dan Jawaban yang Benar yaitu: ";

    DataSoal data = new DataSoal();
    List<Integer> numbers = new ArrayList<Integer>();
    List<String> noTrue = new ArrayList<>();
    List<String> noFalse = new ArrayList<>();

    FirebaseAuth mAuth;
    DatabaseReference scoreRef;
    DatabaseReference trueRef;
    DatabaseReference falseRef;

    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        time = getIntent().getIntExtra("time", 0);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        userName = mAuth.getCurrentUser().getDisplayName();
        scoreRef = FirebaseDatabase.getInstance().getReference().child("Score");
        trueRef = FirebaseDatabase.getInstance().getReference().child("True");
        falseRef = FirebaseDatabase.getInstance().getReference().child("False");
        query = scoreRef.orderByChild("date");

        Toolbar toolbar = findViewById(R.id.toolbar_timer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quiz");

        Calendar calendarDate = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy HH:mm");
        dateTime = currentDate.format(calendarDate.getTime());

        txtNo = findViewById(R.id.txt_nomor);
        txtSoal = findViewById(R.id.txt_soal);
        txtAnswer = findViewById(R.id.txt_answer);
        txtTimer = findViewById(R.id.txt_time);

        rg = findViewById(R.id.rg);
        rb_a = findViewById(R.id.rb_a);
        rb_b = findViewById(R.id.rb_b);
        rb_c = findViewById(R.id.rb_c);
        rb_d = findViewById(R.id.rb_d);
        rb_e = findViewById(R.id.rb_e);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        btn_check = findViewById(R.id.btn_check);
        btn_lanjut.setVisibility(View.INVISIBLE);
        txtAnswer.setVisibility(View.INVISIBLE);

        int numberOfNumbersYouWant = 20;
        Random random = new Random();

        do {
            int next = random.nextInt(20);
            if (!numbers.contains(next) && next!=20){
                numbers.add(next);
                //Log.i("Numbers", "Random Number : " + numbers);
            }
        }while (numbers.size() < numberOfNumbersYouWant);

        setKonten();

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setKonten();
                btn_lanjut.setVisibility(View.INVISIBLE);
                btn_check.setVisibility(View.VISIBLE);
                txtAnswer.setVisibility(View.INVISIBLE);
            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban();
                btn_lanjut.setVisibility(View.VISIBLE);
                btn_check.setVisibility(View.INVISIBLE);
                txtAnswer.setVisibility(View.VISIBLE);
            }
        });
    }

    private void cekJawaban() {
        if (rb_a.isChecked()){
            if (rb_a.getText().toString().equals(jawaban)){
                point = point + 1;
                noTrue.add(nomor);
                txtAnswer.setText(textBenar);
            }else{
                point_lost = point_lost + 1;
                noFalse.add(nomor);
                txtAnswer.setText(textSalah + jawaban);
            }
        }else if (rb_b.isChecked()){
            if (rb_b.getText().toString().equals(jawaban)){
                point = point + 1;
                noTrue.add(nomor);
                txtAnswer.setText(textBenar);
            }else{
                point_lost = point_lost + 1;
                noFalse.add(nomor);
                txtAnswer.setText(textSalah + jawaban);
            }
        }else if (rb_c.isChecked()){
            if (rb_c.getText().toString().equals(jawaban)){
                point = point + 1;
                noTrue.add(nomor);
                txtAnswer.setText(textBenar);
            }else{
                point_lost = point_lost + 1;
                noFalse.add(nomor);
                txtAnswer.setText(textSalah + jawaban);
            }
        }else if (rb_d.isChecked()){
            if (rb_d.getText().toString().equals(jawaban)){
                point = point + 1;
                noTrue.add(nomor);
                txtAnswer.setText(textBenar);
            }else{
                point_lost = point_lost + 1;
                noFalse.add(nomor);
                txtAnswer.setText(textSalah + jawaban);
            }
        }else if (rb_e.isChecked()){
            if (rb_e.getText().toString().equals(jawaban)){
                point = point + 1;
                noTrue.add(nomor);
                txtAnswer.setText(textBenar);
            }else{
                point_lost = point_lost + 1;
                noFalse.add(nomor);
                txtAnswer.setText(textSalah + jawaban);
            }
        }else{
            Toast.makeText(this, "Silahkan Pilih Jawaban Anda", Toast.LENGTH_SHORT).show();
        }
    }

    private void setKonten() {
        rg.clearCheck();
        arr = data.pertanyaan.length;
        //Log.i("Size", "Size : " + arr);
        if(x < arr){
            no = numbers.get(x);
            //Log.i("Nomor", "No : " + arr);
            txtNo.setText(data.getNoSoal(no) + "/" + arr);
            txtSoal.setText(data.getSoal(no));
            rb_a.setText(data.getPilihan(no, 0));
            rb_b.setText(data.getPilihan(no, 1));
            rb_c.setText(data.getPilihan(no, 2));
            rb_d.setText(data.getPilihan(no, 3));
            rb_e.setText(data.getPilihan(no, 4));
            jawaban = data.getJawaban(no);
            nomor = data.getNoSoal(no);
        }else{
            //Toast.makeText(this, "Point: " + point, Toast.LENGTH_SHORT).show();
//            Log.i("Numbers True", "Number : " + noTrue);
//            Log.i("Numbers False", "Number : " + noFalse);
            yourCountDownTimer.onFinish();
        }
        x++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        yourCountDownTimer = new CountDownTimer(time, 1000){
            public void onTick(long millisUntilFinished){
                NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                txtTimer.setText(f.format(min) + ":" + f.format(sec));
            }
            public  void onFinish(){
//                startActivity(new Intent(getApplicationContext(), StartQuizActivity.class));
//                Toast.makeText(TimerActivity.this, "Timer Stop", Toast.LENGTH_SHORT).show();

                int point_zero = 20 - (point + point_lost);

                final String key = "Score-" + userId + dateTime;

                HashMap scoreMap = new HashMap();
                scoreMap.put("test", "Test " + (jumlah+1));
                scoreMap.put("score_key", key);
                scoreMap.put("date", dateTime);
                scoreMap.put("score_point", point);
                scoreMap.put("point_lost", point_lost + point_zero);
                scoreMap.put("user_id", userId);
                scoreMap.put("user_name", userName);

                scoreRef.child(key).updateChildren(scoreMap)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()){
                                    trueRef.child(key).setValue(noTrue);
                                    falseRef.child(key).setValue(noFalse);
                                }
                            }
                        });

                Intent next = new Intent(TimerActivity.this, HasilActivity.class);
                String totalPoint = String.valueOf(point);
                next.putExtra("score", totalPoint);
                startActivity(next);
                finish();
            }
        }.start();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
                        String uid = snap.child("user_id").getValue().toString();
                        if (uid.equals(userId)){
                            jumlah = jumlah+1;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TimerActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

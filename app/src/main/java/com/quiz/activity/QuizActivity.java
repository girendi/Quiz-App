package com.quiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.quiz.R;
import com.quiz.data.DataSoal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private TextView txtNo, txtSoal;
    private RadioGroup rg;
    private RadioButton rb_a, rb_b, rb_c, rb_d, rb_e;
    private Button btn_lanjut;

    int arr, no;
    int x = 0;
    int point = 0;
    String jawaban;

    DataSoal data = new DataSoal();
    List<Integer> numbers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = findViewById(R.id.toolbar_quiz);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quiz");

        txtNo = findViewById(R.id.txt_nomor);
        txtSoal = findViewById(R.id.txt_soal);
        rg = findViewById(R.id.rg);
        rb_a = findViewById(R.id.rb_a);
        rb_b = findViewById(R.id.rb_b);
        rb_c = findViewById(R.id.rb_c);
        rb_d = findViewById(R.id.rb_d);
        rb_e = findViewById(R.id.rb_e);
        btn_lanjut = findViewById(R.id.btn_lanjut);

        int numberOfNumbersYouWant = 20;
        Random random = new Random();

        do {
            int next = random.nextInt(20);
            if (!numbers.contains(next) && next!=20){
                numbers.add(next);
                Log.i("Numbers", "Random Number : " + numbers);
            }
        }while (numbers.size() < numberOfNumbersYouWant);


        setKonten();

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban();
            }
        });

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
        }else{
            //Toast.makeText(this, "Point: " + point, Toast.LENGTH_SHORT).show();
            Intent next = new Intent(QuizActivity.this, HasilActivity.class);
            String totalPoint = String.valueOf(point);
            next.putExtra("score", totalPoint);
            startActivity(next);
        }
        x++;
    }

    private void cekJawaban() {
        if (rb_a.isChecked()){
            if (rb_a.getText().toString().equals(jawaban)){
                point = point + 1;
                setKonten();
            }else{
                setKonten();
            }
        }else if (rb_b.isChecked()){
            if (rb_b.getText().toString().equals(jawaban)){
                point = point + 1;
                setKonten();
            }else{
                setKonten();
            }
        }else if (rb_c.isChecked()){
            if (rb_c.getText().toString().equals(jawaban)){
                point = point + 1;
                setKonten();
            }else{
                setKonten();
            }
        }else if (rb_d.isChecked()){
            if (rb_d.getText().toString().equals(jawaban)){
                point = point + 1;
                setKonten();
            }else{
                setKonten();
            }
        }else if (rb_e.isChecked()){
            if (rb_e.getText().toString().equals(jawaban)){
                point = point + 1;
                setKonten();
            }else{
                setKonten();
            }
        }else{
            Toast.makeText(this, "Silahkan Pilih Jawaban Anda", Toast.LENGTH_SHORT).show();
        }
    }


}

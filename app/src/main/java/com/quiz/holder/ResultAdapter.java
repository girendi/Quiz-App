package com.quiz.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.quiz.R;
import com.quiz.data.Score;

import java.util.List;

public class ResultAdapter extends ArrayAdapter<Score> {

    private Activity activity;
    private List<Score> scores;

    public ResultAdapter(Activity activity, List<Score> scores) {
        super(activity, R.layout.content_result, scores);
        this.activity = activity;
        this.scores = scores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflater.inflate(R.layout.content_result, null, true);

        TextView txtPercobaan = view.findViewById(R.id.txt_percobaan);
        TextView txtTanggal = view.findViewById(R.id.txt_tanggal);
        TextView txtBenar = view.findViewById(R.id.txt_benar);
        TextView txtSalah = view.findViewById(R.id.txt_salah);

        Score score = scores.get(position);

        txtPercobaan.setText(score.getTest());
        txtTanggal.setText(score.getDate());
        txtBenar.setText(Integer.toString(score.score_point));
        txtSalah.setText(Integer.toString(score.point_lost));

        return view;
    }

}

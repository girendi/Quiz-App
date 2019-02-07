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

public class ScoreAdapter extends ArrayAdapter<Score> {

    private Activity activity;
    private List<Score> scores;
    int i = 1;

    public ScoreAdapter(Activity activity, List<Score> scores) {
        super(activity, R.layout.content_score, scores);
        this.activity = activity;
        this.scores = scores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflater.inflate(R.layout.content_score, null, true);

        TextView txtNo = view.findViewById(R.id.txt_nomor);
        TextView txtNama = view.findViewById(R.id.txt_nama);
        TextView txtScore = view.findViewById(R.id.txt_score);

        Score score = scores.get(position);

        int point_score = score.getScore_point();
        txtNo.setText("#" + Integer.toString(i));
        txtNama.setText(score.getUser_name());
        txtScore.setText(Integer.toString(point_score));

        i++;
        return view;
    }
}

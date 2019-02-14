package com.quiz.data;


public class Score {

    public String test, score_key, date, user_id, user_name;

    public int score_point, point_lost;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getScore_key() {
        return score_key;
    }

    public void setScore_key(String score_key) {
        this.score_key = score_key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore_point() {
        return score_point;
    }

    public void setScore_point(int score_point) {
        this.score_point = score_point;
    }

    public int getPoint_lost() {
        return point_lost;
    }

    public void setPoint_lost(int point_lost) {
        this.point_lost = point_lost;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

package com.example.minec.realmp6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final static String API = "http://nflarrest.com/api/v1/";
    public final static String KEY_URL = "com.example.minec.realmp6.URL";
    public final static String KEY_TAG = "com.example.minec.realmp6.TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void resultsToDisplay(String url, String tag) {
        Intent intent = new Intent(this, results.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_TAG, tag);
        startActivity(intent);
    }

    public void popular_crimes(View view) {
        String url = API + "crime?limit=10";
        String tag = "Category";
        resultsToDisplay(url, tag);
    }

    public void team_leader_arrests(View view) {
        String url = API + "team?limit=10";
        String tag = "Team";
        resultsToDisplay(url, tag);
    }

    public void league_leader_arrests(View view) {

    }

    public void position_top_crimes(View view) {

    }

    public void crimes_by_position(View view) {
        String url = API + "position?limit=10";
        String tag = "Position";
        resultsToDisplay(url, tag);
    }
}

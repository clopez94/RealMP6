package com.example.minec.realmp6;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private EditText search;
    private final static String API = "https://nflarrest.com/api/v1/";
    public final static String KEY_URL = "com.example.minec.realmp6.URL";
    public final static String KEY_TAG = "com.example.minec.realmp6.TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (EditText)findViewById(R.id.editText);
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
        String store;
        String editTextStore;
        try {
            editTextStore = URLEncoder.encode(search.getText().toString(), "UTF-8");
            store = API + "crime/topPlayers/" + editTextStore + "?limit=10";
            resultsToDisplay(store,"Name");
        } catch (Exception e) {}
    }

    public void position_top_crimes(View view) {
        String store;
        String editTextStore;
        try {
            editTextStore = URLEncoder.encode(search.getText().toString(), "UTF-8");
            store = API + "position/topCrimes/" + editTextStore + "?limit=10";
            resultsToDisplay(store,"Category");
        } catch (Exception e) {}
    }

    public void crimes_by_position(View view) {
        String url = API + "position?limit=10";
        String tag = "Position";
        resultsToDisplay(url, tag);
    }
    public void player_search(View view) {
        String store;
        String editTextStore;
        try {
            editTextStore = URLEncoder.encode(search.getText().toString(), "UTF-8");
            store = API + "player/arrests/" + editTextStore;
            resultsToDisplay(store,"Category");
        } catch (Exception e) {}
    }
}

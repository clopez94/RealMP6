package com.example.minec.realmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.KEY_URL);
        String tag = intent.getStringExtra(MainActivity.KEY_TAG);
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)u.openConnection();
            JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream()));
            ArrayList<String> data = new ArrayList<String>();
            reader.beginArray();
            while(reader.hasNext()) {
                reader.beginObject();
                data.add(reader.nextString());
                reader.endObject();
            }
            reader.endArray();
            ListView listView = (ListView)findViewById(R.id.results);
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listitem, R.id.viewer, data);
            listView.setAdapter(adapter);
        } catch (Exception ex) {}
    }
}

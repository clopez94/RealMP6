package com.example.minec.realmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.net.ssl.HttpsURLConnection;

public class results extends AppCompatActivity {
    private String[] items;
    private InputStreamReader stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.KEY_URL);
        final String tag = intent.getStringExtra(MainActivity.KEY_TAG);
        final ArrayList<String> data = new ArrayList<String>();

        try {
            final URL u = new URL(url);
            final HttpsURLConnection connection = (HttpsURLConnection) u.openConnection();;
            connection.setRequestMethod("GET");

            Thread thread = new Thread() {
                public void run() {

                    try {
                        stream = new InputStreamReader(connection.getInputStream());
                    } catch (Exception ex) {

                    }
                }
            };
            thread.start();
            thread.join();

            JsonReader reader = new JsonReader(stream);
            reader.beginArray();
            while(reader.hasNext()) {
                reader.beginObject();
                while (reader.hasNext()) {
                    if (reader.nextName().equals(tag))
                        data.add(reader.nextString());
                    else
                        reader.skipValue();
                }
                reader.endObject();
            }
            reader.endArray();

            items = new String[data.size()];

            for (int i = 0; i < items.length; ++i)
                items[i] = data.get(i);

            ListView listView = (ListView)findViewById(R.id.results);
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listitem, R.id.viewer, items);
            listView.setAdapter(adapter);
        } catch (Exception ex) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }
}

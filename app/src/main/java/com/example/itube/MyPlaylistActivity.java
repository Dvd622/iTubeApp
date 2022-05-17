package com.example.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylistActivity extends AppCompatActivity {

    ListView myPlaylistListView;
    List<YoutubeURL> youtubeURLList;
    ArrayList<String> stringYoutubeURLList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);

        myPlaylistListView = findViewById(R.id.myPlaylistListView);

        Intent intentReceive = getIntent();
        String user = intentReceive.getStringExtra("user");

        DatabaseHelper db = new DatabaseHelper(this);

        youtubeURLList = db.fetchAllURL();
        stringYoutubeURLList = new ArrayList<>();


        for (YoutubeURL youtubeURL:youtubeURLList) {
            String URLUser = youtubeURL.getUser();
            if (URLUser.equals(user)) {
                stringYoutubeURLList.add(youtubeURL.getYoutubeURL());
            }
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringYoutubeURLList);
        myPlaylistListView.setAdapter(adapter);

        myPlaylistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = stringYoutubeURLList.get(i);
                Intent intent = new Intent();
                intent.putExtra("url", url);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
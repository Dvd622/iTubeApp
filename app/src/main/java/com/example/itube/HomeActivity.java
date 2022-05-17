package com.example.itube;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText youtubeURLEditText;
    Button playButton;
    Button addToPlaylistButton;
    Button myPlaylistButton;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        youtubeURLEditText = findViewById(R.id.homeYoutubeURLEditText);
        playButton = findViewById(R.id.homePlayButton);
        addToPlaylistButton = findViewById(R.id.homeAddToPlaylistButton);
        myPlaylistButton = findViewById(R.id.homeMyPlaylistButton);

        DatabaseHelper db = new DatabaseHelper(this);

        Intent intentReceive = getIntent();
        user = intentReceive.getStringExtra("user");

        playButton.setOnClickListener(view -> {
            String url = youtubeURLEditText.getText().toString();
            if (url.equals("")) {
                Toast.makeText(this, "Enter video url", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, PlayVideoActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }

        });

        addToPlaylistButton.setOnClickListener(view -> {
            String url;
            url = youtubeURLEditText.getText().toString();
            if (url.equals("")) {
                Toast.makeText(this, "Enter a video URL", Toast.LENGTH_SHORT).show();
            } else {
                YoutubeURL youtubeURL = new YoutubeURL(user, url);
                long result = db.insertURL(youtubeURL);
                if (result>-1) {
                    Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to add to playlist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myPlaylistButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MyPlaylistActivity.class);
            intent.putExtra("user", user);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==1) {
            String url = data.getStringExtra("url");
            youtubeURLEditText.setText(url);
        }
    }
}
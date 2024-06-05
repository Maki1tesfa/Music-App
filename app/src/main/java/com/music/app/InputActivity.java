package com.music.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    EditText titleInput, artistInput, albumInput, yearInput;
    DBHelper dbHelper;
    Button saveButton;
    int songId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        titleInput = findViewById(R.id.titleInput);
        artistInput = findViewById(R.id.artistInput);
        albumInput = findViewById(R.id.albumInput);
        yearInput = findViewById(R.id.yearInput);
        saveButton = findViewById(R.id.saveButton);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        if (intent.hasExtra("songId")) {
            songId = intent.getIntExtra("songId", -1);
            String title = intent.getStringExtra("title");
            String artist = intent.getStringExtra("artist");
            String album = intent.getStringExtra("album");
            int year = intent.getIntExtra("year", 0);

            titleInput.setText(title);
            artistInput.setText(artist);
            albumInput.setText(album);
            yearInput.setText(String.valueOf(year));
            saveButton.setText("Update");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String artist = artistInput.getText().toString();
                String album = albumInput.getText().toString();
                int year = Integer.parseInt(yearInput.getText().toString());

                if (songId == -1) {
                    boolean isInserted = dbHelper.insertSong(title, artist, album, year);
                    if (isInserted) {
                        Toast.makeText(InputActivity.this, "Song Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(InputActivity.this, "Error Adding Song", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    boolean isUpdated = dbHelper.updateSong(songId, title, artist, album, year);
                    if (isUpdated) {
                        Toast.makeText(InputActivity.this, "Song Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(InputActivity.this, "Error Updating Song", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });
    }
}

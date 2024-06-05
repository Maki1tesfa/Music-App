package com.music.app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements SongAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<Song> songList;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        dbHelper = new DBHelper(this);
        songList = new ArrayList<>();
        songAdapter = new SongAdapter(this, songList, this);
        recyclerView.setAdapter(songAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void loadSongs() {
        songList.clear();
        Cursor cursor = dbHelper.getAllSongs();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Songs Available", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                songList.add(new Song(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
            }
        }
        songAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSongs();
    }

    @Override
    public void onEditClick(Song song) {
        Intent intent = new Intent(ListActivity.this, InputActivity.class);
        intent.putExtra("songId", song.getId());
        intent.putExtra("title", song.getTitle());
        intent.putExtra("artist", song.getArtist());
        intent.putExtra("album", song.getAlbum());
        intent.putExtra("year", song.getYear());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Song song) {
        boolean isDeleted = dbHelper.deleteSong(song.getId()) > 0;
        if (isDeleted) {
            Toast.makeText(this, "Song Deleted", Toast.LENGTH_SHORT).show();
            songList.remove(song);
            songAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Error Deleting Song", Toast.LENGTH_SHORT).show();
        }
    }
}

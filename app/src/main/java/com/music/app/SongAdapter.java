package com.music.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.music.app.R;
import com.music.app.Song;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private Context context;
    private ArrayList<Song> songList;
    private OnItemClickListener onItemClickListener;

    public SongAdapter(Context context, ArrayList<Song> songList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.songList = songList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.titleText.setText(song.getTitle());
        holder.artistText.setText(song.getArtist());
        holder.albumText.setText(song.getAlbum());
        holder.yearText.setText(String.valueOf(song.getYear()));

        holder.editButton.setOnClickListener(v -> onItemClickListener.onEditClick(song));
        holder.deleteButton.setOnClickListener(v -> onItemClickListener.onDeleteClick(song));
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, artistText, albumText, yearText;
        Button editButton, deleteButton;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            artistText = itemView.findViewById(R.id.artistText);
            albumText = itemView.findViewById(R.id.albumText);
            yearText = itemView.findViewById(R.id.yearText);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface OnItemClickListener {
        void onEditClick(Song song);
        void onDeleteClick(Song song);
    }
}

package com.example.karaoke_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import database.ReadyDatabase;
import database.Track;
import database.User;

public class TrackAdaptor extends ArrayAdapter<Track> {

    User user;
    boolean visibleSwitch;
    ArrayList<Integer> likedTracks;

    public TrackAdaptor(Activity context, ArrayList<Track> tracks) {
        super(context, 0, tracks);
    }

    public void setVisibleSwitch(boolean visibleSwitch) {
        this.visibleSwitch = visibleSwitch;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLikedTracks(ArrayList<Integer> likedTracks) {
        this.likedTracks = likedTracks;
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.track_list_item, parent, false);
        }
        Track currentTrack = getItem(position);
        TextView authorTextView = listItemView.findViewById(R.id.trackAuthor);
        authorTextView.setText(currentTrack.getAuthor());
        TextView nameTextView = listItemView.findViewById(R.id.trackName);
        nameTextView.setText(currentTrack.getName());
        Switch switch1 = listItemView.findViewById(R.id.switch1);
        if (!visibleSwitch) {
            switch1.setVisibility(View.INVISIBLE);
        } else {
            switch1.setVisibility(View.VISIBLE);
            boolean contains = likedTracks.contains(currentTrack.getId());
            switch1.setChecked(contains);
            switch1.setOnClickListener(view -> {
                if (contains) {
                    (new ReadyDatabase()).removeLike(currentTrack.getId(), user.getEmail());
                }
                if (!contains) {
                    (new ReadyDatabase()).addLike(currentTrack.getId(), user.getEmail());
                }
            });
        }
        return listItemView;
    }
}

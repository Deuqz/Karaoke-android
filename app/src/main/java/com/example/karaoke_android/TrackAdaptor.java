package com.example.karaoke_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
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

    public TrackAdaptor(Activity context, ArrayList<Track> tracks) {
        super(context, 0, tracks);
    }

    public void setVisibleSwitch(boolean visibleSwitch) {
        this.visibleSwitch = visibleSwitch;
    }

    public void setUser(User user) {
        this.user = user;
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
        Switch switch1 = (Switch) listItemView.findViewById(R.id.switch1);
        if (!visibleSwitch) {
            switch1.setVisibility(View.INVISIBLE);
        } else {
            switch1.setVisibility(View.VISIBLE);
            switch1.setChecked(user.containsTrack(currentTrack));
            switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    (new ReadyDatabase()).removeLike(currentTrack, user.getEmail());
                } else {
                    (new ReadyDatabase()).addLike(currentTrack, user.getEmail());
                }
            });
        }
        return listItemView;
    }
}

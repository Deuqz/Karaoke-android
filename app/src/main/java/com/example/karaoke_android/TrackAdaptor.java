package com.example.karaoke_android;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import database.Track;
import database.User;

public class TrackAdaptor extends ArrayAdapter<Track> {

    User user;

    public TrackAdaptor(Activity context, ArrayList<Track> tracks) {
        super(context, 0, tracks);
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Track currentTrack = getItem(position);
        TextView authorTextView = listItemView.findViewById(R.id.track_author);
        authorTextView.setText(currentTrack.getAuthor());
        TextView nameTextView = listItemView.findViewById(R.id.track_name);
        nameTextView.setText(currentTrack.getName());
        return listItemView;
    }
}

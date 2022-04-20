package com.example.karaoke_android;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import database.Track;

public class TrackAdaptor extends ArrayAdapter<Track> {

    private static final String LOG_TAG = TrackAdaptor.class.getSimpleName();

    public TrackAdaptor(Activity context, ArrayList<Track> desserts) {
        super(context, 0, desserts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Track currentTrack = getItem(position);
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.track_author);
        authorTextView.setText(currentTrack.getAuthor());
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.track_name);
        nameTextView.setText(currentTrack.getName());
        return listItemView;
    }
}

package com.example.karaoke_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import database.Track;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MusicFragment extends Fragment {

    static private final ArrayList<Track> allTracks;

    static {
//        TODO fill tracks
        allTracks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            allTracks.add(new Track("Gde nas net", "Oxxxymiron", "", 0));
            allTracks.add(new Track("Lose yourself", "Eminem", "", 0));
            allTracks.add(new Track("Vidihai", "Noize MC", "", 0));
        }
    }

    private ArrayList<Track> getTackList(String subString) {
        ArrayList<Track> tracks = new ArrayList<>();
        for (Track track : allTracks) {
            if (track.getName().toLowerCase().contains(subString.toLowerCase())
                    || track.getAuthor().toLowerCase().contains(subString.toLowerCase())) {
                tracks.add(track);
            }
        }
        return tracks;
    }

    public MusicFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void buttonsRedraw(View view, EditText editText) {
        ArrayList<Track> tracks = getTackList(editText.getText().toString());
        TrackAdaptor trackAdapter = new TrackAdaptor(getActivity(), tracks);
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(trackAdapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), SongActivity.class);
//            TODO putExtra track + user
//            intent.putExtra("Track", (Parcelable) tracks.get(i));
//            intent.putExtra("User", user);
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        EditText editText = view.findViewById(R.id.textInput);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                buttonsRedraw(view, editText);
            }
        });
        buttonsRedraw(view, editText);
        return view;
    }
}

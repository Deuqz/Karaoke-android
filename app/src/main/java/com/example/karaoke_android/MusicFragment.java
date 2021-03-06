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

import database.ReadyDatabase;
import database.Track;
import database.User;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MusicFragment extends Fragment implements View.OnClickListener {

    static private ArrayList<Track> allTracks;
    static private ArrayList<Integer> likedTracks;


    public static MusicFragment newInstance(User userSer) {
        MusicFragment fragment = new MusicFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", userSer);
        fragment.setArguments(bundle);
        return fragment;
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
        allTracks = (new ReadyDatabase()).getDefaultTracks();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void buttonsRedraw(User user, View view, EditText editText) {
        ArrayList<Track> tracks = getTackList(editText.getText().toString());
        TrackAdaptor trackAdapter = new TrackAdaptor(getActivity(), tracks);
        trackAdapter.setVisibleSwitch(true);
        trackAdapter.setUser(user);
        trackAdapter.setLikedTracks(likedTracks);
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(trackAdapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), SongActivity.class);
            intent.putExtra("Track", (Parcelable) tracks.get(i));
            intent.putExtra("User", (Parcelable) user);
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        assert getArguments() != null;
        User user = (User) getArguments().getSerializable("User");
        likedTracks = (ArrayList<Integer>) (new ReadyDatabase()).getLikeTracks(user.getEmail()).stream().map(Track::getId).collect(Collectors.toList());
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
                buttonsRedraw(user, view, editText);
            }
        });
        buttonsRedraw(user, view, editText);
        return view;
    }

    @Override
    public void onClick(View v) {
    }
}

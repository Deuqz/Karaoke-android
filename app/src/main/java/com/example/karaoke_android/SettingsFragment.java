package com.example.karaoke_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import database.ReadyDatabase;
import database.Track;
import database.User;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    public static SettingsFragment newInstance(User userSer) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", userSer);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void buttonsRedraw(User user, View view) {
        ArrayList<Track> tracks = (new ReadyDatabase()).getLikeTracks(user.getEmail());
        Collections.reverse(tracks);
        TrackAdaptor trackAdapter = new TrackAdaptor(getActivity(), tracks);
        trackAdapter.setVisibleSwitch(true);
        trackAdapter.setUser(user);
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        assert getArguments() != null;
        User user = (User) getArguments().getSerializable("User");
        buttonsRedraw(user, view);
        return view;
    }

    @Override
    public void onClick(View v) {
    }
}

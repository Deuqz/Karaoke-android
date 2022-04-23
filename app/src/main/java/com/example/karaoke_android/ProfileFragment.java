package com.example.karaoke_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import database.Track;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        assert getArguments() != null;
        TextView firstName = (TextView) view.findViewById(R.id.firstName);
        firstName.setText(getArguments().getString("userData").split(" ")[0]);
        TextView secondName = (TextView) view.findViewById(R.id.secondName);
        secondName.setText(getArguments().getString("userData").split(" ")[1]);
        TextView email = (TextView) view.findViewById(R.id.email);
        email.setText(getArguments().getString("userData").split(" ")[2]);
//        ArrayList<Track> tracks = getArguments().getString("userData").split(" ");
//        TrackAdaptor trackAdapter = new TrackAdaptor(getActivity(), tracks);
//        ListView listView = (ListView) view.findViewById(R.id.listView);
//        listView.setAdapter(trackAdapter);
//        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
//            Intent intent = new Intent(getActivity(), SongActivity.class);
//            intent.putExtra("trackName", tracks.get(i).toString());
//            startActivity(intent);
//        });
        return view;
    }
}

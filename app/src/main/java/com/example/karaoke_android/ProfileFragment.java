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
import database.User;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(User userSer) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", userSer);
        fragment.setArguments(bundle);
        return fragment;
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
        User user = (User) getArguments().getSerializable("User");
        TextView firstName = view.findViewById(R.id.firstName);
        firstName.setText(user.getFirstName());
        TextView secondName = view.findViewById(R.id.secondName);
        secondName.setText(user.getSecondName());
        TextView email = view.findViewById(R.id.email);
        email.setText(user.getEmail());
//        TODO write trackList, now it is empty
        TrackAdaptor trackAdapter = new TrackAdaptor(getActivity(), user.getTrackList());
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(trackAdapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), SongActivity.class);
//            TODO send track
//            intent.putExtra("trackName", user.getTrackList().get(i).toString());
            startActivity(intent);
        });
        return view;
    }
}

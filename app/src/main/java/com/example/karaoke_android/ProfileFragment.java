package com.example.karaoke_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import database.ReadyDatabase;
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
        User tempUser = (User) getArguments().getSerializable("User");
        User user = (new ReadyDatabase()).getUser(tempUser.getEmail());
        TextView firstName = view.findViewById(R.id.firstName);
        firstName.setText(user.getFirstName());
        TextView secondName = view.findViewById(R.id.secondName);
        secondName.setText(user.getSecondName());
        TextView email = view.findViewById(R.id.email);
        email.setText(user.getEmail());
        TrackAdaptor trackAdapter = new TrackAdaptor(getActivity(), user.getTrackList());
        trackAdapter.setVisibleSwitch(false);

        Log.e("ProfileFragment", String.valueOf((new ReadyDatabase()).getUser(user.getEmail()).getTrackList().size()));
        Log.e("ProfileFragment", String.valueOf(user.getTrackList().size()));

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(trackAdapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), PlayMusicActivity.class);
            intent.putExtra("Track", (Parcelable) user.getTrackList().get(i));
            intent.putExtra("User", (Parcelable) user);
            intent.putExtra("CAME_FROM", 0);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}

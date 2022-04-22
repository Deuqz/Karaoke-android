package com.example.karaoke_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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
        TextView firstName = (TextView) view.findViewById(R.id.firstName);
        assert getArguments() != null;
        firstName.setText(getArguments().getString("userData").split(" ")[0]);
        TextView secondName = (TextView) view.findViewById(R.id.secondName);
        secondName.setText(getArguments().getString("userData").split(" ")[1]);
        return view;
    }
}

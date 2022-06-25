package com.example.karaoke_android;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import database.DataBase;
import database.ReadyDatabase;
import database.User;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment implements View.OnClickListener {

    static private List<User> allUsers;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static SearchFragment newInstance(User userSer) {
        SearchFragment fragment = new SearchFragment(userSer.getEmail());
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", userSer);
        fragment.setArguments(bundle);
        return fragment;
    }

    private List<User> getTackList(String subString) {
        List<User> users = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getEmail().toLowerCase().contains(subString.toLowerCase())
                    || user.getFirstName().toLowerCase().contains(subString.toLowerCase())
                    || user.getSecondName().toLowerCase().contains(subString.toLowerCase())) {
                users.add(user);
            }
        }
        return users;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public SearchFragment(String currentUserEmail) {
        DataBase dataBase = new ReadyDatabase();
        List<String> usersNames = dataBase.getAllUserEmails();
        if (usersNames == null) {
            allUsers = new ArrayList<>();
            return;
        }
        allUsers = usersNames.stream().filter(e -> !e.equals(currentUserEmail)).map(dataBase::getUser).collect(Collectors.toList());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void buttonsRedraw(User user, View view, EditText editText) {
        List<User> users = getTackList(editText.getText().toString());
        UserAdaptor userAdaptor = new UserAdaptor(getActivity(), users);
        userAdaptor.setUser(user);
        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(userAdaptor);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            // intent.putExtra("Track", (Parcelable) tracks.get(i));
            intent.putExtra("User2", (Parcelable) users.get(i));
            intent.putExtra("User", (Parcelable) user);
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        assert getArguments() != null;
        User user = (User) getArguments().getSerializable("User");
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

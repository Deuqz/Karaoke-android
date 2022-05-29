package com.example.karaoke_android;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import database.User;

public class UserAdaptor extends ArrayAdapter<User> {

    User user;

    public UserAdaptor(Activity context, List<User> tracks) {
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
                    R.layout.user_list_item, parent, false);
        }
        User currentUser = getItem(position);
        TextView emailTextView = listItemView.findViewById(R.id.trackEmail);
        emailTextView.setText(currentUser.getEmail());
        TextView firstNameTextView = listItemView.findViewById(R.id.trackFirstName);
        firstNameTextView.setText(currentUser.getFirstName());
        TextView secondNameTextView = listItemView.findViewById(R.id.trackSecondName);
        secondNameTextView.setText(currentUser.getSecondName());
        return listItemView;
    }
}

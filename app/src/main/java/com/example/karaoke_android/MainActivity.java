package com.example.karaoke_android;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import database.User;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        tabLayout = findViewById(R.id.tabLayout);
        TabLayout.Tab profileTab = tabLayout.newTab();
        profileTab.setText("\uD83D\uDC64");
        tabLayout.addTab(profileTab);
        TabLayout.Tab musicTab = tabLayout.newTab();
        musicTab.setText("\uD83C\uDFB6");
        tabLayout.addTab(musicTab);
        TabLayout.Tab settingsTab = tabLayout.newTab();
        settingsTab.setText("❤");
        tabLayout.addTab(settingsTab);
        TabLayout.Tab searchTab = tabLayout.newTab();
        searchTab.setText("\uD83D\uDD0E");
        tabLayout.addTab(searchTab);
        User user = getIntent().getParcelableExtra("User");

        Log.e("MainActivity", String.valueOf(user.getTrackList().size()));

        Fragment fragment = ProfileFragment.newInstance(user);
        startFragment(fragment);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                startFragment(getFragment(tab, user));
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                startFragment(getFragment(tab, user));
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                startFragment(getFragment(tab, user));
            }
        });
    }

    private void startFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        assert fragment != null;
        ft.replace(R.id.frameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Fragment getFragment(TabLayout.Tab tab, User user) {
        Fragment fragment = null;
        switch (tab.getPosition()) {
            case 0:
                fragment = ProfileFragment.newInstance(user);
                break;
            case 1:
                fragment = MusicFragment.newInstance(user);
                break;
            case 2:
                fragment = SettingsFragment.newInstance(user);
                break;
            case 3:
                fragment = SearchFragment.newInstance(user);
                break;
        }
        return fragment;
    }
}

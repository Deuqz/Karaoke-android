package com.example.karaoke_android;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleFrameLayout = findViewById(R.id.simpleFrameLayout);
        tabLayout = findViewById(R.id.simpleTabLayout);
        TabLayout.Tab musicTab = tabLayout.newTab();
        musicTab.setText("\uD83C\uDFB6");
        tabLayout.addTab(musicTab);
        TabLayout.Tab searchTab = tabLayout.newTab();
        searchTab.setText("\uD83D\uDD0E");
        tabLayout.addTab(searchTab);
        TabLayout.Tab settingsTab = tabLayout.newTab();
        settingsTab.setText("⚙");
        tabLayout.addTab(settingsTab);
        TabLayout.Tab profileTab = tabLayout.newTab();
        profileTab.setText("\uD83D\uDC64");
        tabLayout.addTab(profileTab);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new MusicFragment();
                        break;
                    case 1:
                        fragment = new SearchFragment();
                        break;
                    case 2:
                        fragment = new SettingsFragment();
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                assert fragment != null;
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}

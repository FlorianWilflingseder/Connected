package com.example.connected;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {

    private ProfileFragment pf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedFragment()).commit();



        new TwitterManager();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_feed:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pf.getFg()).commit();
                            break;

                        case R.id.nav_profile:
                            pf = new ProfileFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pf).commit();
                            break;

                        case R.id.nav_support:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SupportFragment()).commit();
                            break;
                    }
                    return true;
                }
            };
}

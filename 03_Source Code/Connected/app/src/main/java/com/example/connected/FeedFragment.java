package com.example.connected;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.concurrent.ExecutionException;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class FeedFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //new UpdateStatusTask().execute("ich fik euch alle lg android studio");
        try {
            List<String> list =  new RetrieveTweetsTask().execute("tonimufasa").get();
            for(String s: list){
                System.out.println(s);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }
}

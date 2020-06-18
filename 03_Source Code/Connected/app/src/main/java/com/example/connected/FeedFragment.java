package com.example.connected;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connected.Feed.FeedMessage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


import twitter4j.*;


public class FeedFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FeedMessage> feedMessages = new ArrayList<>();
    private Adapter adapter;

    Profile selectedProfile;
    String twitterName, profileName;
    List<String> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        Bundle bundle = this.getArguments();

        if(bundle != null)
            selectedProfile = new Gson().fromJson(bundle.getString("profile"), Profile.class);

        if(selectedProfile != null){
            twitterName = selectedProfile.getTwitterName();
            profileName = selectedProfile.getProfileName();

            try {
                list = new RetrieveTweetsTask().execute(twitterName).get();
            } catch (Exception e) {  }

            for (int i = 0; i < 10; i++) {
                feedMessages.add(new FeedMessage(profileName, twitterName, list.get(i), "18.06.2020"));
            }
        }


        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new Adapter(feedMessages);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        return rootView;
    }


}

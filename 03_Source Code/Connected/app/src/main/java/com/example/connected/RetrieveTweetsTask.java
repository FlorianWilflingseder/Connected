package com.example.connected;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import twitter4j.ResponseList;
import twitter4j.Status;

public class RetrieveTweetsTask extends AsyncTask<String,Void, List<String>> {
    private Exception exception;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected List<String> doInBackground(String... strings) {
        try{
            ResponseList<twitter4j.Status> statuses = TwitterManager.getinstance().getUserTimeline(strings[0]);
            return statuses.stream().map(
                    item -> item.getText()).collect(Collectors.toList());
        }catch (Exception e){
            this.exception = e;
            return null;
        }
    }
}

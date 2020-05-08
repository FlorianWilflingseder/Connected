package com.example.connected;
import android.os.AsyncTask;

import twitter4j.Status;

class UpdateStatusTask extends AsyncTask<String, Void, String> {
    private Exception exception;

    @Override
    protected String doInBackground(String... st) {

        try {
            twitter4j.Status status = TwitterManager.getinstance().updateStatus(st[0]);
            return status.getText();
        } catch (Exception e) {
            e.printStackTrace();
            this.exception = e;
            return null;
        }
    }


    @Override
    protected void onPostExecute(String status) {
        super.onPostExecute(status);
        System.out.println(status);
    }
}
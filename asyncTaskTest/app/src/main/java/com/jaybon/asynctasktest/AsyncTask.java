package com.jaybon.asynctasktest;

import android.widget.TextView;

public class AsyncTask extends android.os.AsyncTask<Void, String, Void> {

    private TextView tv;

    public AsyncTask(TextView tv) {
        this.tv = tv;
    }

    @Override
    protected Void doInBackground(Void... strings) {

        publishProgress("안녕");

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        tv.setText(values[0].toString());
    }
}

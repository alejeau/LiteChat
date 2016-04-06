package com.excilys.android.formation.chatlite.tasks;

import android.view.View;
import android.widget.ProgressBar;

import com.excilys.android.formation.chatlite.activities.LogInActivity;
import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LogInTask extends android.os.AsyncTask<String, Integer, String> {
    protected LogInActivity activity;
    protected ProgressBar loadingWheel;
    String result = null;
    String textUrl = null;

    LogInTask() {}

    public LogInTask(LogInActivity activity) {
        this.activity = activity;
        loadingWheel = (ProgressBar) this.activity.findViewById(R.id.loadingWheel);
    }

    @Override
    protected void onPreExecute() {
        this.loadingWheel.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params) {
        this.textUrl = "http://formation-android-esaip.herokuapp.com/connect/" + params[0] + "/" + params[1];
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(textUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String res = InputStreamToString.convert(in);
            this.result = res;
        } catch (Exception e) {
        } finally {
            urlConnection.disconnect();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        this.loadingWheel.setVisibility(View.GONE);
    }

    public String getResult() {
        return this.result;
    }

    public String getTextUrl() {
        return this.textUrl;
    }
}

package com.excilys.android.formation.chatlite.tasks;

import android.widget.ProgressBar;

import com.excilys.android.formation.chatlite.activities.LogInActivity;
import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewMessagesTask extends android.os.AsyncTask<String, Void, String> {
    protected LogInActivity activity;
    protected ProgressBar loadingWheel;
    String result = null;

    public ViewMessagesTask() {}

    @Override
    protected void onPreExecute() { }

    @Override
    protected String doInBackground(String... params) {
        String textUrl = "http://formation-android-esaip.herokuapp.com/messages/" + params[0] + "/" + params[1];
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
    protected void onPostExecute(String result) { }

    public String getResult() {
        return this.result;
    }
}

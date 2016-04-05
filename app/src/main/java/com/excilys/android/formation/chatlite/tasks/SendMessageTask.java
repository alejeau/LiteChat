package com.excilys.android.formation.chatlite.tasks;

import android.view.View;
import android.widget.ProgressBar;

import com.excilys.android.formation.chatlite.ParlezVousActivity;
import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendMessageTask extends android.os.AsyncTask<String, Integer, String> {

    public SendMessageTask() {}

    @Override
    protected void onPreExecute() { }

    @Override
    protected String doInBackground(String... params) {
        String textUrl = "http://formation-android-esaip.herokuapp.com/message/" + params[0] + "/" + params[1] + "/" + params[2];
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(textUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.getInputStream();
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (Exception e) {
        } finally {
            urlConnection.disconnect();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) { }
}

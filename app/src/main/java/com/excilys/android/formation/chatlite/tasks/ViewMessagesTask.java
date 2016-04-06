package com.excilys.android.formation.chatlite.tasks;

import android.widget.ProgressBar;

import com.excilys.android.formation.chatlite.activities.LogInActivity;
import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.connection.RestConnection;
import com.excilys.android.formation.chatlite.tools.InputStreamToString;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewMessagesTask extends android.os.AsyncTask<String, Void, String> {
    String result = null;

    public ViewMessagesTask() {}

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... params) {
        this.result = RestConnection.getMessages(params[0], params[1]);
        return this.result;
    }

    @Override
    protected void onPostExecute(String result) { }

    public String getResult() {
        return this.result;
    }
}

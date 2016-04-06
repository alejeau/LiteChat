package com.excilys.android.formation.chatlite.tasks;

import com.excilys.android.formation.chatlite.connection.RestConnection;

import java.net.HttpURLConnection;
import java.net.URL;

public class SendMessageTask extends android.os.AsyncTask<String, Integer, Void> {

    public SendMessageTask() {}

    @Override
    protected void onPreExecute() { }

    @Override
    protected Void doInBackground(String... params) {
        RestConnection rc = RestConnection.INSTANCE;
        rc.sendMessage(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void naught) { }
}

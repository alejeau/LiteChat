package com.excilys.android.formation.chatlite.tasks;

import com.excilys.android.formation.chatlite.connection.OkConnection;

public class ViewMessagesTask extends android.os.AsyncTask<String, Void, String> {
    String result = null;

    public ViewMessagesTask() {}

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... params) {
        this.result = OkConnection.INSTANCE.getMessages(params[0], params[1]);
        return this.result;
    }

    @Override
    protected void onPostExecute(String result) { }

    public String getResult() {
        return this.result;
    }
}

package com.excilys.android.formation.chatlite.tasks;

import com.excilys.android.formation.chatlite.connection.OkConnection;
import com.excilys.android.formation.chatlite.model.SimpleMessage;

public class SendMessageTask extends android.os.AsyncTask<SimpleMessage, Void, Void> {

    public SendMessageTask() {}

    @Override
    protected void onPreExecute() { }

    @Override
    protected Void doInBackground(SimpleMessage... params) {
        OkConnection.INSTANCE.sendMessage(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void naught) { }
}

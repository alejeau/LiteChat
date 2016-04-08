package com.excilys.android.formation.chatlite.tasks;

import android.os.AsyncTask;

import com.excilys.android.formation.chatlite.connection.OkConnection;

public class NetworkAvailabilityCheckTask extends AsyncTask<Void, Void, Boolean> {
    protected Boolean doInBackground(Void... params) {
        Boolean available = OkConnection.checkOnlineAvailability();
        return available;
    }
}

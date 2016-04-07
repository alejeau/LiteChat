package com.excilys.android.formation.chatlite.tasks;

import com.excilys.android.formation.chatlite.connection.OkConnection;
import com.excilys.android.formation.chatlite.model.User;

public class RegisterTask extends android.os.AsyncTask<User, Integer, Boolean> {

    @Override
    protected Boolean doInBackground(User... params) {
        Boolean success = OkConnection.INSTANCE.register(params[0]);
        return success;
    }
}

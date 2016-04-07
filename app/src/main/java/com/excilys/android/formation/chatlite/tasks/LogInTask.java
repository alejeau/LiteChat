package com.excilys.android.formation.chatlite.tasks;

import android.view.View;
import android.widget.ProgressBar;

import com.excilys.android.formation.chatlite.activities.LogInActivity;
import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.connection.RestConnection;
import com.excilys.android.formation.chatlite.mappers.JsonMapper;
import com.excilys.android.formation.chatlite.model.User;


public class LogInTask extends android.os.AsyncTask<User, Integer, Boolean> {
    private final String TAG = LogInTask.class.getSimpleName();

    protected LogInActivity activity;
    protected ProgressBar loadingWheel;
    Boolean result = null;
    String textUrl = null;

    public LogInTask() {}

    public LogInTask(LogInActivity activity) {
        this.activity = activity;
        loadingWheel = (ProgressBar) this.activity.findViewById(R.id.loadingWheel);
    }

    @Override
    protected void onPreExecute() {
        this.loadingWheel.setVisibility(View.VISIBLE);
    }

    @Override
    protected Boolean doInBackground(User... params) {
        RestConnection rc = RestConnection.INSTANCE;
        String tmp = rc.isValidUser(params[0]);
        if (!tmp.equals("")) {
            tmp = JsonMapper.mapLogIn(tmp, "status");
            if (tmp.equals("200")) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        this.loadingWheel.setVisibility(View.GONE);
    }

    public Boolean getResult() {
        return this.result;
    }

    public String getTextUrl() {
        return this.textUrl;
    }
}

package com.excilys.android.formation.chatlite.tasks;

import com.excilys.android.formation.chatlite.connection.RestConnection;
import com.excilys.android.formation.chatlite.model.User;

/**
 * Created by excilys on 07/04/16.
 */
public class RegisterTask extends android.os.AsyncTask<User, Integer, Boolean> {
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Boolean doInBackground(User... params) {
        Boolean success = false;
        success = RestConnection.register(params[0]);
        return success;
    }
}

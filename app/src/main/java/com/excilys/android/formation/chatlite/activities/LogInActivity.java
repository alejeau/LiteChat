package com.excilys.android.formation.chatlite.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.model.User;
import com.excilys.android.formation.chatlite.tasks.LogInTask;
import com.excilys.android.formation.chatlite.tasks.RegisterTask;

import java.util.concurrent.ExecutionException;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = LogInActivity.class.getSimpleName();
    public final static String EXTRA_USERNAME = "com.excilys.android.formation.chatlite.USERNAME";
    public final static String EXTRA_PASSWORD = "com.excilys.android.formation.chatlite.PASSWORD";
    public final static String EXTRA_USER = "com.excilys.android.formation.chatlite.USER";

    SharedPreferences settings = null;

    private EditText usernameField;
    private EditText passwordField;

    private User user = null;
//    private String username = null;
//    private String password = null;

    boolean silentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        // Restore preferences
        settings = getPreferences(MODE_PRIVATE);
        this.silentMode = settings.getBoolean("silentMode", false);
        String username = settings.getString(EXTRA_USERNAME, "Name");
        String password = settings.getString(EXTRA_PASSWORD, "");
        this.user = new User(username, password);
        this.usernameField = (EditText) findViewById(R.id.editTextName);
        this.passwordField = (EditText) findViewById(R.id.editTextPassword);
        this.usernameField.setText(username);
        this.passwordField.setText(password);
    }

    /**
     * Sends a message to the server if and only if the message's not empty.
     *
     * @param v
     */
    public void send(View v) {
        Boolean networkAvailable = true;
//        try {
//            networkAvailable = new NetworkAvailabilityCheckTask().get();
//        } catch (InterruptedException e) {
//            Log.e(TAG, e.getMessage());
//        } catch (ExecutionException e) {
//            Log.e(TAG, e.getMessage());
//        }
        if (networkAvailable) {
            boolean isValid = isValid();
            if (isValid) {
                updateUser();
                LogInTask pvt = new LogInTask(this);
                boolean exists = false;
                try {
                    exists = pvt.execute(user).get();
                } catch (Exception e) {
                }
                if (exists) {
                    startMainMenuActivity();
                } else {
                    Toast.makeText(this, "Invalid username/login!", Toast.LENGTH_SHORT).show();
                }
            } else {
                String s = "Please fill all the fields!";
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No server nor network available!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Resets the fields to their default value.
     *
     * @param v
     */
    public void reset(View v) {
        user = new User("Name", "");
        this.usernameField.setText(user.getUsername());
        this.passwordField.setText(user.getPassword());
    }

    public void register(View v) {

        if (!isValid()) {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
        } else {
            updateUser();
            boolean success = false;
            try {
                success = new RegisterTask().execute(this.user).get();
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            } catch (ExecutionException e) {
                Log.e(TAG, e.getMessage());
            }

            String info = "Username already taken!";
            if (success) {
                info = "Successful registration!";
                startMainMenuActivity();
            }
            Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Checks whether the fields are valid to start a connection (i.e. not empty).
     *
     * @return true if the params are valid, false else
     */
    private boolean isValid() {
        String s = this.usernameField.getText().toString();
        if (s.equals("")) {
            return false;
        }

        s = this.passwordField.getText().toString();
        if (s.equals("")) {
            return false;
        }

        return true;
    }

    public void startMainMenuActivity() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra(EXTRA_USERNAME, user.getUsername());
        intent.putExtra(EXTRA_PASSWORD, user.getPassword());
        startActivity(intent);
    }

    protected void updateUser(){
        user.setUsername(this.usernameField.getText().toString());
        user.setPassword(this.passwordField.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", this.silentMode);
        editor.putString(EXTRA_USERNAME, user.getUsername());
        editor.putString(EXTRA_PASSWORD, user.getPassword());
        // Commit the edits!
        editor.commit();
    }
}

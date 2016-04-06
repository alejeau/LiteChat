package com.excilys.android.formation.chatlite.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.tasks.LogInTask;

public class LogInActivity extends AppCompatActivity {
    public final static String EXTRA_USERNAME = "com.excilys.android.formation.chatlite.USERNAME";
    public final static String EXTRA_PASSWORD = "com.excilys.android.formation.chatlite.PASSWORD";

    SharedPreferences settings = null;

    private EditText usernameField;
    private EditText passwordField;


    String user = null;
    String pass = null;

    boolean silentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        // Restore preferences
        settings = getPreferences(MODE_PRIVATE);
        this.silentMode = settings.getBoolean("silentMode", false);
        this.user = settings.getString(EXTRA_USERNAME, "Name");
        this.pass = settings.getString(EXTRA_PASSWORD, "");
        this.usernameField = (EditText) findViewById(R.id.editTextName);
        this.passwordField = (EditText) findViewById(R.id.editTextPassword);
        this.usernameField.setText(user);
        this.passwordField.setText(pass);
    }

    /**
     * Sends a message to the server if and only if the message's not empty.
     *
     * @param v
     */
    public void send(View v) {
        boolean isValid = isValid();
        if (isValid) {
            user = this.usernameField.getText().toString();
            pass = this.passwordField.getText().toString();
            LogInTask pvt = new LogInTask(this);
            boolean exists = false;
            try {
                exists = pvt.execute(user, pass).get();
            } catch (Exception e) {
            }
            if (exists) {
                Intent intent = new Intent(this, MainMenuActivity.class);
                intent.putExtra(EXTRA_USERNAME, user);
                intent.putExtra(EXTRA_PASSWORD, pass);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid username/login!", Toast.LENGTH_SHORT).show();
            }
        } else {
            String s = "Please fill all the fields!";
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Resets the fields to their default value.
     *
     * @param v
     */
    public void reset(View v) {
        this.user = "Name";
        this.pass = "";
        this.usernameField.setText(user);
        this.passwordField.setText(pass);
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

    @Override
    protected void onStop() {
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", this.silentMode);
        editor.putString(EXTRA_USERNAME, user);
        editor.putString(EXTRA_PASSWORD, pass);
        // Commit the edits!
        editor.commit();
    }
}

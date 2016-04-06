package com.excilys.android.formation.chatlite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.tasks.SendMessageTask;

import java.net.URLEncoder;

public class SendMessageActivity extends AppCompatActivity {
    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sets the toolbar title to "Send message"
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Send message");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.user = intent.getStringExtra(LogInActivity.EXTRA_USERNAME);
        this.pass = intent.getStringExtra(LogInActivity.EXTRA_PASSWORD);
    }

    /**
     * Sends the message to the server.
     */
    public void send() {
        EditText et = (EditText) findViewById(R.id.editTextSendMessage);
        String message = et.getText().toString();
        if (!message.equals("")) {
            try {
                message = URLEncoder.encode(message, "UTF-8");
                message = message.replace("+", "%20");
                new SendMessageTask().execute(user, pass, message).get();
            } catch (Exception e) {
            }
            finish();
        } else {
            Toast.makeText(this, "Cannot send an empty message!", Toast.LENGTH_SHORT).show();
        }

    }

}

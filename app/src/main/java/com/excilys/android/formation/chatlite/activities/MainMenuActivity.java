package com.excilys.android.formation.chatlite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.excilys.android.formation.chatlite.R;

public class MainMenuActivity extends AppCompatActivity {
    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sets the toolbar title to "Main Menu"
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Main menu");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Gets the username and password
        Intent intent = getIntent();
        this.user = intent.getStringExtra(LogInActivity.EXTRA_USERNAME);
        this.pass = intent.getStringExtra(LogInActivity.EXTRA_PASSWORD);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Loads the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuLogout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Starts the ViewMessages activity
     * @param v the view
     */
    public void viewMessages(View v){
        Intent intent = new Intent(this, ViewMessagesActivity.class);
        launch(intent);
    }

    /**
     * Starts the SendMessage activity
     * @param v the view
     */
    public void sendMessage(View v){
        Intent intent = new Intent(this, SendMessageActivity.class);
        launch(intent);
    }

    /**
     * Starts the activity after the username and password are input
     * @param intent the intent to launch
     */
    private void launch(Intent intent) {
        intent.putExtra(LogInActivity.EXTRA_USERNAME, user);
        intent.putExtra(LogInActivity.EXTRA_PASSWORD, pass);
        startActivity(intent);
    }
}

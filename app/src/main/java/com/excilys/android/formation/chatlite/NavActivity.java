package com.excilys.android.formation.chatlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class NavActivity extends AppCompatActivity {
    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Main menu");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        this.user = intent.getStringExtra(ParlezVousActivity.EXTRA_USERNAME);
        this.pass = intent.getStringExtra(ParlezVousActivity.EXTRA_PASSWORD);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    public void viewMessages(View v){
        Intent intent = new Intent(this, ViewMessagesActivity.class);
        launch(intent);
    }

    public void sendMessage(View v){
        Intent intent = new Intent(this, SendMessageActivity.class);
        launch(intent);
    }

    private void launch(Intent intent) {
        intent.putExtra(ParlezVousActivity.EXTRA_USERNAME, user);
        intent.putExtra(ParlezVousActivity.EXTRA_PASSWORD, pass);
        startActivity(intent);
    }
}

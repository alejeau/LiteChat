package com.excilys.android.formation.chatlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.excilys.android.formation.chatlite.mappers.MessagesMapper;
import com.excilys.android.formation.chatlite.tasks.ViewMessagesTask;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewMessagesActivity extends AppCompatActivity {
    private ListView listView;
    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("View messages");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.user = intent.getStringExtra(ParlezVousActivity.EXTRA_USERNAME);
        this.pass = intent.getStringExtra(ParlezVousActivity.EXTRA_PASSWORD);

        listView = (ListView) findViewById(R.id.listViewViewMessages);
        refresh();
    }

    public void refresh(){
        String message = null;
        try {
            message = new ViewMessagesTask().execute(user, pass).get();
        } catch (Exception e) { }

        ArrayList<HashMap<String, String>> list = MessagesMapper.map(message);

        ListAdapter adapter = new SimpleAdapter(this, list, R.layout.row_list, new String[] {"name", "message"}, new int[] { R.id.pseudo, R.id.textMessage });
        listView.setAdapter(adapter);
    }

//    private

}

package com.excilys.android.formation.chatlite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.excilys.android.formation.chatlite.R;
import com.excilys.android.formation.chatlite.mappers.JsonMapper;
import com.excilys.android.formation.chatlite.model.SimpleMessage;
import com.excilys.android.formation.chatlite.tasks.SendMessageTask;
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
//        setContentView(R.layout.fragment_send_message);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("View messages");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                refresh();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.user = intent.getStringExtra(LogInActivity.EXTRA_USERNAME);
        this.pass = intent.getStringExtra(LogInActivity.EXTRA_PASSWORD);

        listView = (ListView) findViewById(R.id.listViewViewMessages);
        refresh();
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
            case R.id.menuRefresh:
                refresh();
                return true;
            case R.id.menuLogout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Refreshes the list of messages
     */
    public void refresh() {
        String messages = null;
        String limit = "1000", offset = "0";
        try {
            messages = new ViewMessagesTask().execute(limit, offset).get();
        } catch (Exception e) {
        }

        // Maps the message String into a ArrayList<HashMap<String, String>>
        ArrayList<HashMap<String, String>> list = JsonMapper.mapMessages(messages);

        // Displays the messages in a ListAdapter
        String[] orga = new String[]{"name", "message"};
        int[] ids = new int[]{R.id.pseudo, R.id.textMessage};
        ListAdapter adapter = new SimpleAdapter(this, list, R.layout.row_list, orga, ids);
        listView.setAdapter(adapter);
        listView.setSelection(list.size() - 1);
    }



    /**
     * Sends the message to the server.
     */
    public void fragmentSendMessage(View v) {
        EditText et = (EditText) findViewById(R.id.editTextFragment);
        String content = et.getText().toString();
        if (!content.equals("")) {
            SimpleMessage m = new SimpleMessage(this.user, content);
            try {
                new SendMessageTask().execute(m);
            } catch (Exception e) {
            }
            et.setText("");
            refresh();
        } else {
            Toast.makeText(this, "Cannot send an empty message!", Toast.LENGTH_SHORT).show();
        }
    }

}

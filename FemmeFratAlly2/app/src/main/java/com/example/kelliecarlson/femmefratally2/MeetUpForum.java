package com.example.kelliecarlson.femmefratally2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;

/**
 * Created by kelliecarlson on 2/20/16.
 */
public class MeetUpForum extends AppCompatActivity {

    //have all the different areas for a meet up post
    //access from database, show them, delete them after 24h
    //look at timestamp

    Firebase myFirebaseRef = new Firebase("https://blistering-torch-4059.firebaseio.com/");


    public String college = "UPenn";
    public java.util.Date currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.meet_up_forum);
        Calendar calendar = Calendar.getInstance();
        currentDate = calendar.getTime();

        Intent intent = getIntent();
        college = intent.getStringExtra("college");

        final ListView listView = (ListView) findViewById(R.id.listView2);

        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Use Firebase to populate the list.
        Firebase.setAndroidContext(this);

        String newURL = "https://blistering-torch-4059.firebaseio.com/colleges/" + college;
        new Firebase(newURL)
                .addChildEventListener(new ChildEventListener() {
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        MeetUpPost post = (MeetUpPost) dataSnapshot.child("Meetups").getValue();
                        if (currentDate.getTime() - post.getDate().getTime() < 86400*1000) {
                            adapter.add((String) dataSnapshot.child("Meetups").getValue());
                        }
                    }

                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        adapter.remove((String) dataSnapshot.child("Meetups").getValue());
                    }

                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void backButton(View view) {
        Intent intent = new Intent(this, FratMainScreen.class);
        startActivity(intent);
    }

    public void writeNewPost(View view) {
        Intent intent = new Intent(this, NewMeetUpPost.class);
        Bundle bundle = new Bundle();
        bundle.putString("college", college);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}



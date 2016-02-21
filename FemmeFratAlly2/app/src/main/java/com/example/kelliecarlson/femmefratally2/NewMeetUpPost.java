package com.example.kelliecarlson.femmefratally2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.Calendar;

/**
 * Created by sharoniegreenblatt on 2/20/16.
 */
public class NewMeetUpPost extends AppCompatActivity {
    Firebase myFirebaseRef = new Firebase("https://blistering-torch-4059.firebaseio.com/");
    Intent schoolFratInfo = getIntent();
    public String universityName = schoolFratInfo.getStringExtra("college");
    public String fraternityName = schoolFratInfo.getStringExtra("frat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.write_a_new_meet_up_post);
    }

    public void back(View view){
        Intent intent = new Intent(this, MeetUpForum.class);
        startActivity(intent);
    }

    public void post(View view){
        TextView when = (TextView) findViewById(R.id.editText7);
        TextView locat = (TextView) findViewById(R.id.editText8);
        TextView dest = (TextView) findViewById(R.id.editText9);
        TextView other = (TextView) findViewById(R.id.editText10);

        String whent = when.getText().toString();
        String locate = locat.getText().toString();
        String desti = dest.getText().toString();
        String othert = other.getText().toString();

        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();

        MeetUpPost stuff = new MeetUpPost(whent, locate, desti, othert, date);


        String newURL = "https://blistering-torch-4059.firebaseio.com/colleges/" + universityName +"/Meetups";

        Firebase postRef = new Firebase(newURL);

        postRef.push().setValue(stuff);

        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        final String s = extrasBundle.getString("college");

        String newURL1 = "https://blistering-torch-4059.firebaseio.com/colleges/" + s +"/Meetups/";

        Firebase postRefs = new Firebase(newURL1);

        postRefs.push().setValue(stuff);
        
        Intent intent = new Intent(this, MeetUpForum.class);
        startActivity(intent);

    }


}

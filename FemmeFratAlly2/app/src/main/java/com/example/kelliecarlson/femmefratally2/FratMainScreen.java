package com.example.kelliecarlson.femmefratally2;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;


/**
 * Created by kelliecarlson on 2/20/16.
 */
public class FratMainScreen extends AppCompatActivity {

    Firebase myFirebaseRef = new Firebase("https://blistering-torch-4059.firebaseio.com/");
    Intent schoolFratInfo = getIntent();
    public String universityName = schoolFratInfo.getStringExtra("college");
    public String fraternityName = schoolFratInfo.getStringExtra("frat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.frat_main_screen);

        TextView schoolTextElement = (TextView) findViewById(R.id.textView15);
        schoolTextElement.setText(universityName);
        TextView fratTextElement = (TextView) findViewById(R.id.textView8);
        fratTextElement.setText(fraternityName);

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

    public void inTheNews(View view) {
        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
        search.putExtra(SearchManager.QUERY, universityName + " " + fraternityName);
        startActivity(search);
    }

    public void meetUp(View view) {
        Intent intent = new Intent(this, MeetUpForum.class);
        Bundle bundle = new Bundle();
        bundle.putString("college", universityName);
        bundle.putString("fraternity", fraternityName);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void reviewsComments(View view) {
        Intent intent = new Intent(this, ReviewCommentForum.class);
        Bundle bundle = new Bundle();
        bundle.putString("college", universityName);
        bundle.putString("fraternity", fraternityName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
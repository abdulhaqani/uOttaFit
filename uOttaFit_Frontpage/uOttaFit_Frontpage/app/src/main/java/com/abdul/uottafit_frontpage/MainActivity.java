package com.abdul.uottafit_frontpage;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int breakTime=30;
    public static int numReps=10;
    Button startButton;
    EditText timeEditText;
    private int requestCodeTemp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.buttonStart);
        timeEditText = (EditText) findViewById(R.id.editText3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.v);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onStartButtonClick(View view){

        String exercises[]=new String[8];
        exercises[0]="pushups";
        exercises[1]="situps";
        exercises[2]="burpees";
        exercises[3]="squats";
        exercises[4]="plank";
        exercises[5]="squat-jumps";
        exercises[6]="single leg deadlifts";
        exercises[7]="jumping-jacks";

        requestCodeTemp = 0;
        Log.d("uOttaFit","onStartClicked");
        Toast.makeText(this,"Start button clicked",Toast.LENGTH_SHORT).show();

        String duration = timeEditText.getText().toString();

        if (duration.length() == 0){
            Toast.makeText(this, "Please Enter the time", Toast.LENGTH_LONG).show();

        }

        try{
            Random rand=new Random();

            int mins = Integer.parseInt(duration);
            int sec = mins*60;
            Toast.makeText(this, "Timer set for notification", Toast.LENGTH_LONG).show();

            for(int start=100;start < sec ;){
                //if (editText3
                int randomNum = rand.nextInt(8);


                Log.d("uOttaFit", "Forloop  :  "  + start + "  "  );
                scheduleNotification( getNotification("Do " + MainActivity.numReps + " " + exercises[randomNum]),start*100);
                start = start + 100;
                NotificationPublisher.NOTIFICATION_ID = NotificationPublisher.NOTIFICATION_ID + requestCodeTemp;
            }

        }
        catch(NumberFormatException e){

            Toast.makeText(this, "Please enter the number", Toast.LENGTH_LONG).show();
        }





    }

    public void onSettingsButtonClick(View view){

        Log.d("uOttaFit","onSettingsClicked");
        Intent myItent = new Intent(MainActivity.this,settingsActivity.class);
        MainActivity.this.startActivity(myItent);
    }

    private void scheduleNotification(Notification notification, int delay) {

        Log.d("uOttaFit", "Notification registered  :   " + delay);
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCodeTemp, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        requestCodeTemp++;
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Workout Time");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        return builder.getNotification();
    }
}

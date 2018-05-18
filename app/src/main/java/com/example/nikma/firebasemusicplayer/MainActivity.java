package com.example.nikma.firebasemusicplayer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout mlayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    ViewPager viewPager;

    Timer timer;

    private NavigationView navigationView;
    RelativeLayout layout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager recyclerLM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        layout = findViewById(R.id.tre);
        viewPager = (ViewPager)findViewById(R.id.vpheader);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.logout){
                    logout();
                }else if(id==R.id.account){
                    Toast.makeText(getApplicationContext(),"account",Toast.LENGTH_LONG).show();
                }else if(id==R.id.settings){
                    Toast.makeText(getApplicationContext(),"settings",Toast.LENGTH_LONG).show();

                }
                return true;

            }
        });



        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mlayout, R.string.open, R.string.close);
        mlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRecyclerView();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new mytimer(), 5000, 3000);


    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        recyclerLM = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(recyclerLM);
        mRecyclerView.setAdapter(new CustomerAdapter(this));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private int logout() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
                finish();
            }
        });
        return 0;
    }

    class mytimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    }else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(0);

                    }
                }
            });


        }
    }

}

package com.example.akusei.pruebaintents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
       // navView = (NavigationView)findViewById(R.id.navview);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.videos);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        navView = (NavigationView)findViewById(R.id.navview);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        //Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                Intent iContactos = new Intent(MainActivity.this, ContactosActivity.class);
                                startActivity(iContactos);
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                Intent iImagenes=new Intent(MainActivity.this,ImagenesActivity.class);
                                startActivity(iImagenes);
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_3:
                                Intent iVideo=new Intent(MainActivity.this,VideoActivity.class);
                                startActivity(iVideo);
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_4:
                                Intent iAudio=new Intent(MainActivity.this,MusicActivity.class);
                                startActivity(iAudio);
                        }
                        menuItem.setChecked(true);
                        getSupportActionBar().setTitle(menuItem.getTitle());
                        //drawerLayout.closeDrawers();

                        return true;
                    }
                });



    }

    public void irImagenes(View view){
        Intent iImagenes=new Intent(MainActivity.this,ImagenesActivity.class);
        startActivity(iImagenes);
    }
    public void irContactos(View view){
        Intent iContactos=new Intent(MainActivity.this,ContactosActivity.class);
        startActivity(iContactos);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

            switch(item.getItemId()) {
                case android.R.id.home:
                    drawerLayout.openDrawer(GravityCompat.START);
                    return true;
                //...
            }

            return super.onOptionsItemSelected(item);
        }

}

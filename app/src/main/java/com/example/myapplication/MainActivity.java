package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    Fragment fragment;
    FragmentTransaction transaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        firstFragmentDisplay(R.id.nav_home);


    }

    private void firstFragmentDisplay(int itemId) {

        fragment = null;

        switch (itemId) {
            case R.id.nav_home:
                fragment = new DashboardFragment();
                break;
            case R.id.nav_account:
                fragment = new AccountFragment();
                break;
            case R.id.nav_about:
                fragment = new AboutFragment();
                break;
            case R.id.nav_f1:
                fragment = new KebudayaanFragment();
                break;
            case R.id.nav_f2:
                fragment = new SejarahFragment();
                break;
        }

        if (fragment != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fLayout, fragment);
            transaction.commit();
        }

        drawer.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        firstFragmentDisplay(item.getItemId());
        return true;
    }
}


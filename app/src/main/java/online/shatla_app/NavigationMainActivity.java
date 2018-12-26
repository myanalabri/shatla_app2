package online.shatla_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import online.shatla_app.forestNursery.ForestNurseryFragment;
import online.shatla_app.fruitNursery.FruitNurseryFragment;
import online.shatla_app.gardens.GardenFragment;
import online.shatla_app.homePage.HomePageFragment;
import online.shatla_app.login.LoginActivity;
import online.shatla_app.login.RegistrationActivity;
import online.shatla_app.ornamentalNursery.OrnamentalNurseryFragment;
import online.shatla_app.profile.NewProdectFragment;
import online.shatla_app.profile.ProductFragment;
import online.shatla_app.tools.ToolFragment;
import online.shatla_app.vegatableNursery.VegatableNurseryFragment;


public class NavigationMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomePageFragment.OnFragmentInteractionListener,
        FruitNurseryFragment.OnFragmentInteractionListener,
        VegatableNurseryFragment.OnFragmentInteractionListener,
        OrnamentalNurseryFragment.OnFragmentInteractionListener,
        ForestNurseryFragment.OnFragmentInteractionListener,
        ToolFragment.OnFragmentInteractionListener,
        GardenFragment.OnFragmentInteractionListener, ProductFragment.OnFragmentInteractionListener,NewProdectFragment.OnFragmentInteractionListener{
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        HomePageFragment homeFragment=HomePageFragment.newInstance(null,null);
        fragmentTransaction.replace(R.id.main_container,homeFragment);
        fragmentTransaction.commit();



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
        getMenuInflater().inflate(R.menu.navigation_main, menu);
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

        if (id == R.id.log_in) {
            Intent intent=new Intent(NavigationMainActivity.this, LoginActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.rigistration) {
            Intent intent=new Intent(NavigationMainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        } else if (id == R.id.about_us) {
            Intent intent=new Intent(NavigationMainActivity.this, AboutusActivity.class);
            startActivity(intent);

        } else if (id == R.id.contact) {
            Intent intent=new Intent(NavigationMainActivity.this, ConnectusActivity.class);
            startActivity(intent);

        }else if (id == R.id.contact) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

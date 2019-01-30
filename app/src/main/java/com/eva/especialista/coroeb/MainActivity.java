package com.eva.especialista.coroeb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.widget.TextView;


import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Button btn ;
TextView tvUsuarioActual;
ProgressDialog progressDialog;
    private AdView mAdView;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.eva.especialista.coroeb.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.eva.especialista.coroeb.R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        btn=(Button)findViewById(com.eva.especialista.coroeb.R.id.btn);
        tvUsuarioActual=(TextView)findViewById(com.eva.especialista.coroeb.R.id.tvUsuarioAcyual);
        tvUsuarioActual.setText(mAuth.getCurrentUser().getEmail());
        progressDialog= new ProgressDialog(this);


        MobileAds.initialize(this,
                "ca-app-pub-9724909472487763~4800665387");

        mAdView = (AdView) findViewById(com.eva.especialista.coroeb.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        progressDialog.setMessage("Espere");
      Intent e = new Intent(MainActivity.this, Practicar.class);
      startActivity(e);
      finish();
      progressDialog.dismiss();

    }
});


        DrawerLayout drawer = (DrawerLayout) findViewById(com.eva.especialista.coroeb.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.eva.especialista.coroeb.R.string.navigation_drawer_open, com.eva.especialista.coroeb.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.eva.especialista.coroeb.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.eva.especialista.coroeb.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.eva.especialista.coroeb.R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.eva.especialista.coroeb.R.id.action_settings) {
            System.exit(0);
            return true;
        }
        if (id== com.eva.especialista.coroeb.R.id.menuCerrarSesion){
            mAuth.signOut();
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
            finish();
        }
        if (id== R.id.hacer_Sugerencia){

            Intent i = new Intent(this,Sugerencias_Activity.class);
            startActivity(i);

            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
      /*  int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(com.eva.especialista.coroeb.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
  }


    public void onClickBtnIrSugerencia(View view) {

        Intent i = new Intent(this,Sugerencias_Activity.class);
        startActivity(i);

        finish();
    }
}

package com.example.especialista.coroeb.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eva.especialista.coroeb.R;
import com.example.especialista.coroeb.LoginActivity;


public class Spash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        Intent x= new Intent(Spash.this, LoginActivity.class);
        startActivity(x);
        finish();
    }

},3000);



    }

}

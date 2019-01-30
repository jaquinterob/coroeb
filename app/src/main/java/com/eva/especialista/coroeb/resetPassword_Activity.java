package com.eva.especialista.coroeb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassword_Activity extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText correo;
    Button btnEnviar;
    FirebaseAuth mAuth;
    TextInputLayout tilCorreo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.eva.especialista.coroeb.R.layout.activity_reset_password_);
        Toolbar toolbar = (Toolbar) findViewById(com.eva.especialista.coroeb.R.id.toolbar);
        setSupportActionBar(toolbar);
        correo=(EditText)findViewById(com.eva.especialista.coroeb.R.id.txtRestablecerPasswordCorreo);
        btnEnviar= (Button)findViewById(com.eva.especialista.coroeb.R.id.btnRestablecerPassword);
        tilCorreo=(TextInputLayout)findViewById(com.eva.especialista.coroeb.R.id.tilRestablecercontraseñaCorreo);
       mAuth=FirebaseAuth.getInstance();
       progressDialog=new ProgressDialog(this);

    }


    private void restablecerPassword(final String correo){
        mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               progressDialog.dismiss();
                Toast.makeText(resetPassword_Activity.this, "Se ha enviado un e-mail a '"+correo+"' con el fin de restablecer la contraseña.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(resetPassword_Activity.this, LoginActivity.class);
            startActivity(i);
            finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent  intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onclickEnviarCorreo(View view) {

        progressDialog.setMessage("Enviando correo");
        progressDialog.show();

        if (Patterns.EMAIL_ADDRESS.matcher(correo.getText().toString()).matches()==false){
            tilCorreo.setError("Correo Inválido");
progressDialog.dismiss();
        }else{

            tilCorreo.setError(null);
            restablecerPassword(correo.getText().toString());
        }

    }
}

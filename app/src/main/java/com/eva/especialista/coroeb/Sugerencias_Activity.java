package com.eva.especialista.coroeb;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eva.especialista.coroeb.Objetos.Sugerencia;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sugerencias_Activity extends AppCompatActivity {
    EditText txtSugerenciaNombre, txtSugenrenciaApellido, txtSugerenciaCorreo, txtSugerenciaCelular, txtSugerenciaComo;
    TextInputLayout tilSugerenciaNombre, tilSugenrenciaApellid, tilSugerenciaCorreo, tilSugerenciaCelular, tilSugerenciaComo;
    boolean validacionNombre = false, validacionCorreo = false, validacionEnQue = false;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.eva.especialista.coroeb.R.layout.activity_sugerencias);
        Toolbar toolbar = (Toolbar) findViewById(com.eva.especialista.coroeb.R.id.toolbar);
        setSupportActionBar(toolbar);

        //casteo

        txtSugerenciaNombre = (EditText) findViewById(R.id.txtSugerenciaNombre);
        txtSugenrenciaApellido = (EditText) findViewById(R.id.txtSugerenciaApellido);
        txtSugerenciaCorreo = (EditText) findViewById(R.id.txtSugerenciaCorreo);
        txtSugerenciaCelular = (EditText) findViewById(R.id.txtSugerenciaCelular);
        txtSugerenciaComo = (EditText) findViewById(R.id.txtSugerenciaComo);

        tilSugerenciaNombre = (TextInputLayout) findViewById(R.id.tilSugerenciaNombre);
        tilSugenrenciaApellid = (TextInputLayout) findViewById(R.id.tilSugerenciaApellido);
        tilSugerenciaCorreo = (TextInputLayout) findViewById(R.id.tilSugerenciaCorreo);
        tilSugerenciaCelular = (TextInputLayout) findViewById(R.id.tilSugerenciaCelular);
        tilSugerenciaComo = (TextInputLayout) findViewById(R.id.tilSugerenciaComo);
        progressDialog = new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent u = new Intent(this, MainActivity.class);
        startActivity(u);
        finish();
    }

    public void onclickSugerenciaEnviar(View view) {

        progressDialog.setMessage("Enviando tu solicitud.");
        progressDialog.show();

        //TODO: validacionNombre
        if (txtSugerenciaNombre.getText().toString().length() < 1) {
            progressDialog.dismiss();
            validacionNombre = false;

            tilSugerenciaNombre.setError("Por favor escribe tu nombre");
        } else {
            validacionNombre = true;
            tilSugerenciaNombre.setError(null);
        }

        //TODO: validacionCorreo
        if (Patterns.EMAIL_ADDRESS.matcher(txtSugerenciaCorreo.getText().toString()).matches() == false) {
            tilSugerenciaCorreo.setError("Correo Inválido");
            validacionCorreo = false;
            progressDialog.dismiss();

        } else {
            validacionCorreo = true;
            tilSugerenciaCorreo.setError(null);
        }

        //TODO:validacionEnQue

        if (txtSugerenciaComo.getText().toString().length() < 1) {
            progressDialog.dismiss();
            validacionEnQue = false;
            tilSugerenciaComo.setError("Por favor escribe una sugerencia");
        } else {
            validacionEnQue = true;
            tilSugerenciaComo.setError(null);
        }


        if (validacionNombre && validacionCorreo && validacionEnQue) {
            GuardarAyudaEnDatabase();
        }


    }

    private void GuardarAyudaEnDatabase() {
        Sugerencia sugerencia = new Sugerencia(txtSugerenciaNombre.getText().toString(),
                txtSugenrenciaApellido.getText().toString(), txtSugerenciaCorreo.getText().toString(),
                txtSugerenciaCelular.getText().toString(), txtSugerenciaComo.getText().toString());
        DatabaseReference myRef = firebaseDatabase.getReference("sugerencias");

        myRef.push().setValue(sugerencia).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(Sugerencias_Activity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(Sugerencias_Activity.this, "Información enviada, Gracias!", Toast.LENGTH_SHORT).show();


                    progressDialog.dismiss();
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Sugerencias_Activity.this);
                    dialogo1.setTitle("Genial!");
                    dialogo1.setMessage("Hemos guardado tu sugerencia, gracias por ayudarnos");
                    dialogo1.setCancelable(true);
                    dialogo1.setIcon(com.eva.especialista.coroeb.R.drawable.mail);
                    System.out.print(task.getException());
                    dialogo1.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent p = new Intent(Sugerencias_Activity.this, MainActivity.class);
                            startActivity(p);
                            finish();
                        }

                    });
                    dialogo1.create();
                    dialogo1.show();


                }
            }
        });
    }
}
package com.example.especialista.coroeb;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eva.especialista.coroeb.R;
import com.example.especialista.coroeb.Objetos.Ayuda;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NecesitoAyudaActivity extends AppCompatActivity {
    Button btnAyudaEnviar;
    TextInputLayout tilAyudaNombre,tilAyudaApellido,tilAyudaCorreo, tilAyudaCelular,tilAyudaEnQue;
    EditText txtAyudaNombre,txtAyudaApellido, txtAyudaCorreo,txtAyudaCelular,txtAyudaEnQue;
    boolean validacionNombre=false,validacionCorreo=false,validacionEnQue=false;
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necesito_ayuda);
        //TODO: casteos
        btnAyudaEnviar=(Button)findViewById(R.id.btnAyudaEnviar);
        tilAyudaNombre=(TextInputLayout)findViewById(R.id.tilAyudaNombre);
        tilAyudaApellido=(TextInputLayout)findViewById(R.id.tilAyudaApellido);
        tilAyudaCorreo=(TextInputLayout)findViewById(R.id.tilAyudaCorreo);
        tilAyudaCelular=(TextInputLayout)findViewById(R.id.tilAyudaCelular);
        tilAyudaEnQue=(TextInputLayout)findViewById(R.id.tilAyudaEnQue);
        txtAyudaNombre=(EditText)findViewById(R.id.txtAyudaNombre);
        txtAyudaApellido=(EditText)findViewById(R.id.txtAyudaApellido);
        txtAyudaCorreo=(EditText)findViewById(R.id.txtAyudaCoreo);
        txtAyudaCelular=(EditText)findViewById(R.id.txtAyudaCelular);
        txtAyudaEnQue=(EditText)findViewById(R.id.txtAyudaEnQue);
        progressDialog= new ProgressDialog(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent u= new Intent(this, LoginActivity.class);
        startActivity(u);
        finish();
    }

    public void onclickAyudaEnviar(View view) {
        progressDialog.setMessage("Enviando tu solicitud.");
        progressDialog.show();

       //TODO: validacionNombre
        if (txtAyudaNombre.getText().toString().length()<1){
            progressDialog.dismiss();
            validacionNombre=false;

            tilAyudaNombre.setError("Por favor escribe tu nombre");
        }else{
            validacionNombre=true;
            tilAyudaNombre.setError(null);
        }

        //TODO: validacionCorreo
        if (Patterns.EMAIL_ADDRESS.matcher(txtAyudaCorreo.getText().toString()).matches()==false){
            tilAyudaCorreo.setError("Correo Inválido");
            validacionCorreo=false;
            progressDialog.dismiss();

        }else{
            validacionCorreo=true;
            tilAyudaCorreo.setError(null);
        }

        //TODO:validacionEnQue

        if (txtAyudaEnQue.getText().toString().length()<1){
            progressDialog.dismiss();
            validacionEnQue=false;
            tilAyudaEnQue.setError("Por favor escribe en qué te podemos ayudar");
        }else{
            validacionEnQue=true;
            tilAyudaEnQue.setError(null);
        }


        if (validacionNombre && validacionCorreo && validacionEnQue){
            GuardarAyudaEnDatabase();
        }


    }

    public void GuardarAyudaEnDatabase(){

        Ayuda nuevaAyuda=new Ayuda(txtAyudaNombre.getText().toString(),txtAyudaApellido.getText().toString(),
                txtAyudaCorreo.getText().toString(),txtAyudaCelular.getText().toString(),txtAyudaEnQue.getText().toString());

        DatabaseReference myRef=firebaseDatabase.getReference("Ayudas");
        myRef.push().setValue(nuevaAyuda).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(NecesitoAyudaActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                }else{
                    Toast.makeText(NecesitoAyudaActivity.this, "Información enviada, Gracias!", Toast.LENGTH_SHORT).show();


                    progressDialog.dismiss();
                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(NecesitoAyudaActivity.this);
                    dialogo1.setTitle("Genial!");
                    dialogo1.setMessage("Hemos guardado tu solicitud, nos pondremos en contacto lo antes posible.");
                    dialogo1.setCancelable(true);
                    dialogo1.setIcon(R.drawable.mail);
                    System.out.print(task.getException());
                    dialogo1.setPositiveButton("Entendido", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which){
                            Intent p=new Intent(NecesitoAyudaActivity.this,LoginActivity.class);
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

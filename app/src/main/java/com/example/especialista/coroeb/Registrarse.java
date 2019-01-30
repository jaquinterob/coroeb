package com.example.especialista.coroeb;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.eva.especialista.coroeb.R;
import com.example.especialista.coroeb.Objetos.Usuario;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrarse extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btnRegistrarUsuario;
    TextView tvUsuarioActual;
    EditText txtRegNombre, txtRegApellido, txtRegCorreo, txtRegPassword, txtConfirmarPassword;
    static final String ERROR_CORREO_YA_EXISTE="com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account.";
    static final String ERROR_CONEXION_A_INTERNET="com.google.firebase.FirebaseNetworkException: A network error (such as timeout, interrupted connection or unreachable host) has occurred.";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        mAuth = FirebaseAuth.getInstance();
final TextInputLayout tilRegNombre, tilRegApellido, tilRegCorreo, tilRegPassword, tilRegConfirmarPassword;
        progressDialog= new ProgressDialog(this);


        //Casteo
        tvUsuarioActual=(TextView)findViewById(R.id.tvUsuarioAcyual);

        btnRegistrarUsuario=(Button)findViewById(R.id.btnResgistrarUsuario);
txtRegNombre=(EditText)findViewById(R.id.txtRegNombre);
txtRegApellido=(EditText)findViewById(R.id.txtRegApellido);
txtRegCorreo=(EditText)findViewById(R.id.txtRegCorreo);
txtRegPassword=(EditText)findViewById(R.id.txtRegContraseña);
txtConfirmarPassword=(EditText)findViewById(R.id.txtRegConfirmarContraseña);
tilRegNombre= (TextInputLayout) findViewById(R.id.tilRegNombre);
tilRegApellido= (TextInputLayout)findViewById(R.id.tilRegApellido);
tilRegCorreo=(TextInputLayout)findViewById(R.id.tilRegCorreo);
tilRegConfirmarPassword=(TextInputLayout)findViewById(R.id.tilRegConfirmarPassword);
tilRegPassword=(TextInputLayout)findViewById(R.id.tilRegPassword);



btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       /**/

       boolean validacionCorreo=false,validacionPasswordIgualdad=false,validacionNombre=false,validacionApellido=false,validacionPasswordLength=false;

       progressDialog.setMessage("Enviando solicitud al servidor");
       progressDialog.show();

       //TODO: validación nombre

       if(txtRegNombre.getText().toString().length()<1){
           tilRegNombre.setError("Debe ingresar un nombre");
           validacionNombre=false;
           progressDialog.dismiss();
       }else{
           validacionNombre=true;
           tilRegNombre.setError(null);
       }

       //TODO: validación Apellido

        if(txtRegApellido.getText().toString().length()<1){
            tilRegApellido.setError("Debe ingresar un Apellido");
           validacionApellido=false;
            progressDialog.dismiss();
        }else{
            tilRegApellido.setError(null);
            validacionApellido=true;
        }


//TODO:validación Correo
if (Patterns.EMAIL_ADDRESS.matcher(txtRegCorreo.getText().toString()).matches()==false){
tilRegCorreo.setError("Correo Inválido");
validacionCorreo=false;
    progressDialog.dismiss();
}else{
    validacionCorreo=true;
    tilRegCorreo.setError(null);
}

//TODO: validación contraseña por confirmación
if(txtRegPassword.getText().length()!=txtConfirmarPassword.getText().length()){

tilRegConfirmarPassword.setError("Las contraseñas no coinciden");
tilRegPassword.setError("Las contraseñas no coinciden");
validacionPasswordIgualdad=false;
    progressDialog.dismiss();
}else{
    validacionPasswordIgualdad=true;
    tilRegPassword.setError(null);
    tilRegConfirmarPassword.setError(null);
}

        if(txtRegPassword.getText().toString().compareTo(txtConfirmarPassword.getText().toString())!=0){

            tilRegConfirmarPassword.setError("Las contraseñas no coinciden");
            tilRegPassword.setError("Las contraseñas no coinciden");
            validacionPasswordIgualdad=false;
            progressDialog.dismiss();
        }else{
            validacionPasswordIgualdad=true;
            tilRegPassword.setError(null);
            tilRegConfirmarPassword.setError(null);
        }

//TODO:validación contraseña por tamaño
if(txtConfirmarPassword.getText().toString().length()<6 && txtConfirmarPassword.getText().toString().length()<6){
    tilRegConfirmarPassword.setError("La contraseña debe tener 6 caracteres como mínimo");
    tilRegPassword.setError("La contraseña debe tener 6 caracteres como mínimo");
    validacionPasswordLength=false;
    progressDialog.dismiss();
}else{
    validacionPasswordLength=true;

}


//TODO: validación total y Registro en base de datos
        if(validacionNombre==true && validacionApellido==true && validacionCorreo==true && validacionPasswordIgualdad==true && validacionPasswordLength==true ){

    RegistrarUsuario();


        }

    }
});


    }

    public void RegistrarUsuario(){


        mAuth.createUserWithEmailAndPassword(txtRegCorreo.getText().toString(), txtRegPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public static final String TAG = "Hola, ";


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Se creó nuevo usuario satisfactoriamente.");
                            FirebaseUser  user = mAuth.getCurrentUser();
                            progressDialog.dismiss();

                               GuargarUsuarioEnDatabase();

                               user.sendEmailVerification();

                               mAuth=null;
                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Registrarse.this);
                            dialogo1.setTitle("A solo un paso...");
                            dialogo1.setMessage("Le hemos enviado un e-mail que contiene instrucciones para activar su nueva cuenta.\nPorfavor revise su cuenta de correo electrónico y siga las instrucciones. \nLuego de esto estará habilitada su cuenta y podrá acceder a CoroEB APP");
                            dialogo1.setCancelable(true);
                            dialogo1.setIcon(R.drawable.mail);
                            dialogo1.setPositiveButton("ok. Revisaré mi correo", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which){
                                    Intent i = getBaseContext().getPackageManager()
                                            .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                   // Intent o=new Intent(Registrarse.this,LoginActivity.class);
                                   // startActivity(o);
                                }

                            });
                            //dialogo1.setIcon(drawable.ic_menu_slideshow);
                           dialogo1.create();
                           dialogo1.show();


                        } else {

                            if (task.getException().toString().equals(ERROR_CORREO_YA_EXISTE)){

                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Registrarse.this);
                                dialogo1.setTitle("Error al crear nuevo Usuario");
                                dialogo1.setMessage("El correo "+txtRegCorreo.getText().toString()+
                                        " ya se encuentra registrado en la base de datos. Restablesca la contraseña con el botón 'OLVIDÉ MI CONTRASEÑA'");
                                dialogo1.setCancelable(true);
                                dialogo1.setIcon(R.drawable.mail);
                                System.out.print(task.getException());
                                dialogo1.setPositiveButton("ok. La restableceré", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which){
                                        Intent o=new Intent(Registrarse.this,LoginActivity.class);
                                        startActivity(o);
                                        finish();
                                    }

                                });
                                //dialogo1.setIcon(drawable.ic_menu_slideshow);
                                dialogo1.create();
                                dialogo1.show();
                            }

                            if (task.getException().toString().equals(ERROR_CONEXION_A_INTERNET)){
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Registrarse.this);
                                dialogo1.setTitle("Error al crear nuevo Usuario");
                                dialogo1.setMessage("La conexión a internet falló!\nAsegurate de que estas conectado a la red wifi o que tienes encendidos los datos moviles y vuelve a intentarlo.");
                                dialogo1.setCancelable(true);
                                dialogo1.setIcon(R.drawable.mail);
                                System.out.print(task.getException());
                                dialogo1.setPositiveButton("ok. voy a revisar", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which){

                                    }

                                });
                                //dialogo1.setIcon(drawable.ic_menu_slideshow);
                                dialogo1.create();
                                dialogo1.show();
                            }

                        }

                        // ...
                    }
                });
    }
    public void GuargarUsuarioEnDatabase(){
        Usuario nuevoUsuario= new Usuario(txtRegNombre.getText().toString(), txtRegApellido.getText().toString(),txtRegCorreo.getText().toString());
        DatabaseReference myRef=database.getReference("usuario");
        myRef.push().setValue(nuevoUsuario);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent y=new Intent(this, LoginActivity.class);
        startActivity(y);
        finish();
    }
}

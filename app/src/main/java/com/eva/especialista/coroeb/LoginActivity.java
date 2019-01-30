package com.eva.especialista.coroeb;

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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
TextInputLayout tilLoginCorreo, tilLoginPassword;
static final String ERROR_CONTRASEÑA="com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password.";
static  final String ERROR_USUARIO="com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted.";
ProgressDialog progressDialog;
   EditText mCorreo, mPassword;

    boolean validacionCorreo=false,validacionPassword=false, validacionCorreoVerificado=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.eva.especialista.coroeb.R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

//Casteo
        mCorreo=(EditText) findViewById(com.eva.especialista.coroeb.R.id.txtCorreo);
        mPassword=(EditText)findViewById(com.eva.especialista.coroeb.R.id.txtPassword);
        tilLoginCorreo= (TextInputLayout) findViewById(com.eva.especialista.coroeb.R.id.tilLoginCorreo);
        tilLoginPassword= (TextInputLayout) findViewById(com.eva.especialista.coroeb.R.id.tilLoginPassword);



        authStateListener= new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    FirebaseUser user= firebaseAuth.getCurrentUser();

        if (user!=null){
            if (!user.isEmailVerified()){
                Toast.makeText(LoginActivity.this, "Verifica tu usuario antes de intentar iniciar sesión", Toast.LENGTH_SHORT).show();
            }else {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
};

}

    @Override
    public void onStart() {
        super.onStart();
       firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void onclickRegistrarse(View view) {
        Intent i=new Intent(this,Registrarse.class);
        startActivity(i);
    }

    public void onclickIniciarSesion(View view) {
//TODO: esperando

        progressDialog.setMessage("Iniciando sesión");
        progressDialog.show();



//TODO: validaciión de Correo
        if (Patterns.EMAIL_ADDRESS.matcher(mCorreo.getText().toString()).matches()==false){
            tilLoginCorreo.setError("Correo Inválido");
            validacionCorreo=false;
            progressDialog.dismiss();
        }else{
            validacionCorreo=true;
            tilLoginCorreo.setError(null);
        }

        //TODO: validación password
        if(mPassword.getText().toString().length()<6){
            tilLoginPassword.setError("La contraseña debe tener 6 caracteres como mínimo");
            validacionPassword=false;
            progressDialog.dismiss();
        }else{
            validacionPassword=true;
            tilLoginPassword.setError(null);

        }

        //TODO: validar i el correo esta verificado
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

if(firebaseUser!=null && !firebaseUser.isEmailVerified()){

    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(LoginActivity.this);
    dialogo1.setTitle("Verificacion de su cuenta");
    dialogo1.setMessage("Estamos comprobando que se haya verificado su cuenta por e-mail");
    dialogo1.setCancelable(true);
    dialogo1.setIcon(com.eva.especialista.coroeb.R.drawable.mail);
    dialogo1.setPositiveButton("Está bien", new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which){
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

    });
    //dialogo1.setIcon(drawable.ic_menu_slideshow);
    dialogo1.create();
    dialogo1.show();
}

        //TODO: validación total
        if (validacionCorreo==true && validacionPassword==true){

            firebaseAuth.signInWithEmailAndPassword(mCorreo.getText().toString(),mPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    tilLoginCorreo.setError(null);
                    tilLoginPassword.setError(null);
                    if (!task.isSuccessful()){
                        progressDialog.dismiss();

                        if (task.getException().toString().equals(ERROR_CONTRASEÑA)){

                            tilLoginPassword.setError("Contraseña incorrecta.");

                        }
                        if (task.getException().toString().equals(ERROR_USUARIO)){

                            tilLoginCorreo.setError("El usuario con el correo '"+mCorreo.getText().toString()+"' no se encuentra en la base de datos o fue eliminado.");

                        }
                        if(task.getException().toString().equals(Registrarse.ERROR_CONEXION_A_INTERNET)){
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(LoginActivity.this);
                            dialogo1.setTitle("Error al intentar iniciar sesión");
                            dialogo1.setMessage("La conexión a internet falló!\nAsegurate de que estas conectado a la red wifi o que tienes encendidos los datos moviles y vuelve a intentarlo.");
                            dialogo1.setCancelable(true);
                            dialogo1.setIcon(com.eva.especialista.coroeb.R.drawable.mail);
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



                    }else{
                        progressDialog.dismiss();
                       /* Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();*/
                    }
                }
            });
        }

    }

    public void olvideMiPassword(View view) {
Intent i= new Intent(this,resetPassword_Activity.class);
startActivity(i);
finish();
    }

    public void necesitoAyuda(View view) {
Intent o=new Intent(this,NecesitoAyudaActivity.class);
startActivity(o);
finish();
    }
}



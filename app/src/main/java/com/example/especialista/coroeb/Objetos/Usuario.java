package com.example.especialista.coroeb.Objetos;

/**
 * Created by Especialista on 26/03/2018.
 */

public class Usuario {
    String nombre,Apellido,correo;

    public Usuario(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        Apellido = apellido;
        this.correo = correo;
    }
    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

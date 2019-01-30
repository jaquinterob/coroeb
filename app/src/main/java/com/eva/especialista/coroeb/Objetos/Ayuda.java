package com.eva.especialista.coroeb.Objetos;

/**
 * Created by Especialista on 4/04/2018.
 */

public class Ayuda {
    String nombre,apellido,correo,celular,EnQue;

    public Ayuda(String nombre, String apellido, String correo, String celular, String enQue) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        EnQue = enQue;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEnQue() {
        return EnQue;
    }

    public void setEnQue(String enQue) {
        EnQue = enQue;
    }
}

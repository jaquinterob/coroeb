package com.eva.especialista.coroeb.Objetos;

import android.text.Editable;

/**
 * Created by Especialista on 26/04/2018.
 */

public class Sugerencia {

    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String sugerencia;

    public Sugerencia(String nombre, String apellido, String correo, String celular, String sugerencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.sugerencia = sugerencia;
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

    public String getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia(String sugerencia) {
        this.sugerencia = sugerencia;
    }
}

package com.ilerna.pac_uf2_bejaranojose;

import java.io.Serializable;

/**
 * Data Transfer Object
 */
public class UsuarioDTO implements Serializable {

    private String nombre, apellido, usuario, pass, email;
    private int id;

    public UsuarioDTO(){}

    public UsuarioDTO(int id, String nombre, String apellido,
                      String usuario, String pass, String email) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.pass = pass;
        this.email = email;
    }

    @Override
    public String toString() {
        return "\n\n\n\t" + nombre +
                "\n\n\t" + apellido +
                "\n\n\t" + usuario +
                "\n\n\t" + pass  +
                "\n\n\t" + email;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

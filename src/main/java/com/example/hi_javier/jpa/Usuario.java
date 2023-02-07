package com.example.hi_javier.jpa;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "tareas")
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "NIF", nullable = false, length = 9)
    private String nif;
    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 30)
    private String nombre;
    @Basic
    @Column(name = "APELLIDOS", nullable = true, length = 50)
    private String apellidos;
    @Basic
    @Column(name = "PW", nullable = true, length = 200)
    private String pw;
    @Basic
    @Column(name = "ACTIVO", nullable = true)
    private int activo;


    public Usuario(String nif, String nombre, String apellidos, String pw, int activo) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pw = pw;
        this.activo = activo;
    }

    public Usuario() {
        this.nif = "";
        this.nombre = "";
        this.pw = "";
        this.activo = 1;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }


}

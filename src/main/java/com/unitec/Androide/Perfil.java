package com.unitec.Androide;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Perfil {
    
    @Id
    String id;
    
    private String nombre;
    private String paterno;
    private String email;
    private String celular;
    private int edad;

    @Override
    public String toString() {
        return "Perfil{" + "nombre=" + nombre + ", paterno=" + paterno + ", email=" + email + ", celular=" + celular + ", edad=" + edad + '}';
    }
    
       
    
    public Perfil(){
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}

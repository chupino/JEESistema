/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author mauri
 */
public class Cursos {
    int codigo;
    String nombre;
    double costo;
    Date fec_ini;
    Date fec_fin;
    int duracion;
    int sesiones;
    int capacidades;
    int inscripciones;
    String estado;
    
    public Cursos(){
        
    }

    public Cursos(int codigo, String nombre, double costo, Date fec_ini, Date fec_fin, int duracion, int sesiones, int capacidades, int inscripciones, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
        this.duracion = duracion;
        this.sesiones = sesiones;
        this.capacidades = capacidades;
        this.inscripciones = inscripciones;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(Date fec_ini) {
        this.fec_ini = fec_ini;
    }

    public Date getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(Date fec_fin) {
        this.fec_fin = fec_fin;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getSesiones() {
        return sesiones;
    }

    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }

    public int getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(int capacidades) {
        this.capacidades = capacidades;
    }

    public int getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(int inscripciones) {
        this.inscripciones = inscripciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}

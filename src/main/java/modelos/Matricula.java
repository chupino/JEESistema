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
public class Matricula {
    int codigo;
    String nombreAlumno;
    String nroDocumento;
    Date fecha;
    String estado;
    String cursos;
    double total;
    
    public Matricula(){
        
    }

    public Matricula(int codigo, String nombreAlumno, String nroDocumento, Date fecha, String estado, String cursos, double total) {
        this.codigo = codigo;
        this.nombreAlumno = nombreAlumno;
        this.nroDocumento = nroDocumento;
        this.fecha = fecha;
        this.estado = estado;
        this.cursos = cursos;
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

  

    
}

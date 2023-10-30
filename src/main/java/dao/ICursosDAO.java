/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelos.Cursos;

/**
 *
 * @author mauri
 */
public interface ICursosDAO {
    public boolean register(Cursos alumno);
    public List<Cursos> obtener();
    public boolean actualizar(Cursos alumno);
    public boolean eliminar(String[] cods);
    public Cursos buscar(int cod);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelos.Alumnos;
import modelos.Cursos;
import modelos.Matricula;

/**
 *
 * @author mauri
 */
public interface IMatriculaDAO {
    public List<Alumnos> buscarAlumnos(Alumnos alumno);
    public List<Matricula> obtener();
    public List<Matricula> buscar(Matricula matricula);
    public List<Cursos> buscarCursos();
    public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos);
}

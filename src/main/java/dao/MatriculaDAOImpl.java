/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;
import modelos.Alumnos;
import modelos.Conexion;
import modelos.Cursos;
import modelos.Matricula;

/**
 *
 * @author mauri
 */
public class MatriculaDAOImpl implements IMatriculaDAO {
    
    @Override
    public List<Alumnos> buscarAlumnos(Alumnos alumno) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM alumnos WHERE nombre LIKE'%" + alumno.getNombre() + "%'";
        List<Alumnos> listaAlumnos = new ArrayList<Alumnos>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Alumnos nalumno = new Alumnos();
                nalumno.setCodigo(rs.getInt(1));
                nalumno.setNombre(rs.getString(2));
                nalumno.setDireccion(rs.getString(3));
                nalumno.setEmail(rs.getString(4));
                nalumno.setTelefono(rs.getString(5));
                nalumno.setCelular(rs.getString(6));
                nalumno.setSexo(rs.getString(7));
                nalumno.setFec_nac(rs.getDate(8));
                nalumno.setEstado(rs.getString(9));
                listaAlumnos.add(nalumno);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método buscarAlumnos");
        }
        return listaAlumnos;
    }

    @Override
    public List<Cursos> buscarCursos() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos ORDER BY codigo";
        List<Cursos> listaCursos = new ArrayList<Cursos>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cursos curso = new Cursos();
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                curso.setCosto(rs.getInt(3));
                curso.setFec_ini(rs.getDate(4));
                curso.setFec_fin(rs.getDate(5));
                curso.setDuracion(rs.getInt(6));
                curso.setSesiones(rs.getInt(7));
                curso.setCapacidades(rs.getInt(8));
                curso.setInscripciones(rs.getInt(9));
                curso.setEstado(rs.getString(10));
                listaCursos.add(curso);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método obtener");
        }
        return listaCursos;
    }

    public String getFecha() {
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }

    @Override
    public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos) {
        Conexion co = new Conexion();
        String xcodm = co.generarCodigo("matriculas", "codigo");
        boolean registrar = true;
        Statement stm = null;
        Connection con = null;

        String sql = "insert into matriculas (codigo,fecha,nro_doc,"
                + "codigo_alumno,total,estado) values (?,?,?,?,?,'A') ";
        String xfech = this.getFecha();
        String xndoc = datosMatricula[0];
        String xcoda = datosMatricula[1];
        String xtota = datosMatricula[2];

        try {
            con = co.Conectar();
            stm = con.createStatement();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, xcodm);
            ps.setString(2, xfech);
            ps.setString(3, xndoc);
            ps.setString(4, xcoda);
            ps.setString(5, xtota);
            ps.executeUpdate();

            for (int xc = 0; xc < codigoCursos.length; xc++) {
                String xcodCurso = codigoCursos[xc];
                this.grabarNuevoDetalle(con, xcodm, xcodCurso, montos[xc]);
            }

            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImpl, "
                    + "método grabarMatricula");
            e.printStackTrace();
            return false;
        }
        return registrar;
    }

    public void grabarNuevoDetalle(Connection xcon, String xcodm, String xcodc, String xmonto)
            throws SQLException {
        String sql = "insert into detalles (codigo_matricula,codigo_curso,"
                + "monto,asistencias,nota,estado) values (?,?,?,0,0,'A') ";

        PreparedStatement ps = xcon.prepareStatement(sql);
        ps.setString(1, xcodm);
        ps.setString(2, xcodc);
        ps.setString(3, xmonto);
        ps.executeUpdate();

        // actualizar nro de inscritos en curso
        sql = "update cursos set inscritos=inscritos+1 where codigo=?";
        PreparedStatement psc = xcon.prepareStatement(sql);
        psc.setString(1, xcodc);
        psc.executeUpdate();
    }

    @Override
    public List<Matricula> obtener() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = 
"SELECT m.codigo,\n" +
"       a.nombre AS NombreAlumno, \n" +
"       m.nro_doc AS NroDoc,\n" +
"       m.fecha AS FechaMatricula, \n" +
"       d.estado AS EstadoMatricula, \n" +
"       GROUP_CONCAT(c.nombre) AS CursosMatriculados, \n" +
"       SUM(d.monto) AS TotalPagado\n" +
"FROM alumnos a\n" +
"INNER JOIN matriculas m ON a.codigo = m.codigo_alumno\n" +
"INNER JOIN detalles d ON m.codigo = d.codigo_matricula\n" +
"INNER JOIN cursos c ON d.codigo_curso = c.codigo\n" +
"GROUP BY m.codigo, a.nombre, m.fecha, d.estado\n" +
"ORDER BY a.nombre, m.fecha;";
        List<Matricula> listaMatriculas = new ArrayList<>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setCodigo(rs.getInt(1));
                matricula.setNombreAlumno(rs.getString(2));
                matricula.setNroDocumento(rs.getString(3));
                matricula.setFecha(rs.getDate(4));
                matricula.setEstado(rs.getString(5));
                matricula.setCursos(rs.getString(6));
                matricula.setTotal(rs.getDouble(7));
                listaMatriculas.add(matricula);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException E) {
            System.out.println("Error: Clase CursoDaoImpl,"
                    + "metodo obtener");
            System.err.println(E.getMessage());
        }
        return listaMatriculas;
    }

    @Override
    public List<Matricula> buscar(Matricula matricula) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT m.codigo,\n" +
"       a.nombre AS NombreAlumno, \n" +
"       m.nro_doc AS NroDoc,\n" +
"       m.fecha AS FechaMatricula, \n" +
"       d.estado AS EstadoMatricula, \n" +
"       GROUP_CONCAT(c.nombre) AS CursosMatriculados, \n" +
"       SUM(d.monto) AS TotalPagado\n" +
"FROM alumnos a\n" +
"INNER JOIN matriculas m ON a.codigo = m.codigo_alumno\n" +
"INNER JOIN detalles d ON m.codigo = d.codigo_matricula\n" +
"INNER JOIN cursos c ON d.codigo_curso = c.codigo\n" +
"WHERE m.nro_doc = '"+ matricula.getNroDocumento() +"' \n" +
"GROUP BY m.codigo, a.nombre, m.fecha, d.estado\n" +
"ORDER BY a.nombre, m.fecha;";
        List<Matricula> listaMatriculas = new ArrayList<Matricula>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Matricula _matricula = new Matricula();
                _matricula.setCodigo(rs.getInt(1));
                _matricula.setNombreAlumno(rs.getString(2));
                _matricula.setNroDocumento(rs.getString(3));
                _matricula.setFecha(rs.getDate(4));
                _matricula.setEstado(rs.getString(5));
                _matricula.setCursos(rs.getString(6));
                _matricula.setTotal(rs.getDouble(7));
                listaMatriculas.add(_matricula);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:Clase MatriculaDaoImpl,"
                    + "método buscarAlumnos");
        }
        return listaMatriculas;
    }

}

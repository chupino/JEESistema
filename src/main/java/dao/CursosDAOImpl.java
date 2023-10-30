/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Alumnos;
import modelos.Conexion;
import modelos.Cursos;

/**
 *
 * @author mauri
 */
public class CursosDAOImpl implements ICursosDAO{

    @Override
    public boolean register(Cursos curso) {
                Conexion co = new Conexion();
        String xco = co.generarCodigo("cursos", "codigo");
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO cursos values (" + xco + ","
                 + "'" + curso.getNombre() + "'," + curso.getCosto() + ", "
        + "'" + curso.getFec_ini() + "','" + curso.getFec_fin() + "', "
        + curso.getDuracion() + ", " + curso.getSesiones() + ", "
        + curso.getCapacidades() + "," + curso.getInscripciones() + ", "
        + "'" + curso.getEstado() + "')";
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException E) {
            System.out.println("Error: Clase CursoDaoImpl, "
                    + "metodo registrar");
            E.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Cursos> obtener() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos ORDER BY codigo";
        List<Cursos> listaCursos = new ArrayList<>();

        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cursos curso = new Cursos();
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                curso.setCosto(rs.getDouble(3));
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
        } catch (SQLException E) {
            System.out.println("Error: Clase CursoDaoImpl,"
                    + "metodo obtener");
            System.err.println(E.getMessage());
        }
        return listaCursos;
    }

    @Override
    public boolean actualizar(Cursos curso) {
                Conexion co = new Conexion();
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE cursos SET "
        + "nombre='" + curso.getNombre() + "', costo='" + curso.getCosto() + "', "
        + "fec_ini='" + curso.getFec_ini() + "', fec_fin='" + curso.getFec_fin() + "', "
        + "duracion='" + curso.getDuracion() + "', sesiones='" + curso.getSesiones() + "', "
        + "capacidad='" + curso.getCapacidades() + "', inscritos='" + curso.getInscripciones() + "', "
        + "estado='" + curso.getEstado() + "' "
        + "WHERE codigo='" + curso.getCodigo() + "'";

        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            actualizar = true;            
            stm.close();
            con.close();
        } catch (SQLException E) {
            System.out.println("Error: Clase AlumnoDaoImpl, "
                    + "metodo registrar");
            E.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(String[] cods) {
                Conexion co = new Conexion();
        boolean eliminar = false;
        Statement stm = null;
        Connection con = null;
        if (cods.length > 0) {
            String sql = "DELETE FROM cursos WHERE codigo in ( ";
            boolean inicio = true;
            for (int xc = 0; xc < cods.length; xc++) {
                sql += inicio ? "?" : ",?";
                inicio = false;
            }
            sql += ")";
            try {
                con = co.Conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                for (int xc = 0; xc < cods.length; xc++) {
                    ps.setString(xc + 1, cods[xc]);
                }
                eliminar = ps.executeUpdate() == 1;
                con.close();
            } catch(SQLException e) {
                System.out.println("Error: Clase CursosDaoImpl, "+
                        "metodo eliminar");
                e.printStackTrace();
            }
        }
        return eliminar; 
    }

    @Override
    public Cursos buscar(int cod) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos WHERE codigo=" + cod;
        Cursos curso = new Cursos();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                curso.setCosto(rs.getDouble(3));
                curso.setFec_ini(rs.getDate(4));
                curso.setFec_fin(rs.getDate(5));
                curso.setDuracion(rs.getInt(6));
                curso.setSesiones(rs.getInt(7));
                curso.setCapacidades(rs.getInt(8));
                curso.setInscripciones(rs.getInt(9));
                curso.setEstado(rs.getString(10));
            }
            stm.close();
            rs.close();
            co.close();
        } catch(SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImpl, "+
                    "metodo buscar");
            e.printStackTrace();
        }
        return curso;
    }
    
}

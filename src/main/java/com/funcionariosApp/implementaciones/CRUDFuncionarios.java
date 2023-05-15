/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.funcionariosApp.implementaciones;

import com.funcionariosApp.dao.Conexion;
import com.funcionariosApp.modelos.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class CRUDFuncionarios implements CRUDFuncionariosI {

    private String crear = "insert into funcionarios(tipoIdentificacion, numeroIdentificacion, "
            + "nombres, apellidos,estadoCivil, sexo, direccion, telefono, fechaNacimiento, "
            + "fk_grupoFamiliar, fk_estudios) values (?,?,?,?,?,?,?,?,?,?,?);";
    private String actualizar = "update funcionarios set tipoIdentificacion = ?, "
            + "nombres = ?, apellidos = ?,estadoCivil = ?, sexo = ?, direccion = ?, telefono = ? where numeroIdentificacion = ?";
    private String eliminar = "delete from funcionarios where numeroIdentificacion = ?";
    private String listar = "select * from funcionarios";
    private String buscar = "select * from funcionarios where numeroIdentificacion = ?";

    Conexion conexion = Conexion.getConexion();

    @Override
    public void crearFuncionario(Funcionario funcionario) {

        try {
            Connection conectar = conexion.conectarBaseDeDatos();
            PreparedStatement crear = conectar.prepareStatement(this.crear);

            crear.setString(1, funcionario.getTipoIdentificacion());
            crear.setString(2, funcionario.getNumeroIdentificacion());
            crear.setString(3, funcionario.getNombres());
            crear.setString(4, funcionario.getApellidos());
            crear.setString(5, funcionario.getEstadoCivil());
            crear.setString(6, funcionario.getSexo());
            crear.setString(7, funcionario.getDireccion());
            crear.setString(8, funcionario.getTelefono());
            crear.setDate(9, funcionario.getFechaNacimiento());
            crear.setInt(10, funcionario.getFk_grupoFamiliar());
            crear.setInt(11, funcionario.getFk_estudios());

            crear.executeUpdate();

            conexion.desconectarBaseDatos();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }

    @Override
    public void actualizarFuncionario(Funcionario funcionario, String numIden) {
        // TODO: Actualizar funcionario
        try {
            Connection conectar = conexion.conectarBaseDeDatos();
            PreparedStatement actualizar = conectar.prepareStatement(this.actualizar);
            
            actualizar.setString(1, funcionario.getTipoIdentificacion());
            actualizar.setString(2, funcionario.getNombres());
            actualizar.setString(3, funcionario.getApellidos());
            actualizar.setString(4, funcionario.getEstadoCivil());
            actualizar.setString(5, funcionario.getSexo());
            actualizar.setString(6, funcionario.getDireccion());
            actualizar.setString(7, funcionario.getTelefono());
            actualizar.setString(8, numIden);

            actualizar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionario actualizado correctamente");

            conexion.desconectarBaseDatos();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }

    @Override
    public void eliminarFuncionario(String numIden) {
        try {
            Connection conectar = conexion.conectarBaseDeDatos();
            PreparedStatement eliminar = conectar.prepareStatement(this.eliminar);
            
            eliminar.setString(1, numIden);
            eliminar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionario eliminado correctamente");
            
            conexion.desconectarBaseDatos();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
    }

    @Override
    public List<Object[]> mostrarFuncionarios() {
        List<Object[]> listaFuncionarios = new ArrayList<>();

        try {
            Connection conectar = conexion.conectarBaseDeDatos();
            PreparedStatement mostrar = conectar.prepareStatement(this.listar);
            ResultSet consulta = mostrar.executeQuery();

            while (consulta.next()) {                
                Object[] funcionario = new Object[12];
                for (int i = 0; i < funcionario.length; i++) {
                    funcionario[i] = consulta.getObject(i+1);
                }
                listaFuncionarios.add(funcionario);
            }
            conexion.desconectarBaseDatos();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        } finally {
            return listaFuncionarios;
        }
    }

    @Override
    public Object[] buscarFuncionario(String numIden) {
        Object[] funcionario = new Object[12];
        try {
            Connection conectar = conexion.conectarBaseDeDatos();
            PreparedStatement buscar = conectar.prepareStatement(this.buscar);
            ResultSet consulta;
            
            buscar.setString(1, numIden);
            consulta = buscar.executeQuery();
            
            while (consulta.next()) {                
                for (int i = 0; i < funcionario.length; i++) {
                    funcionario[i] = consulta.getObject(i+1);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        } finally {
            return funcionario;
        }
    }

}

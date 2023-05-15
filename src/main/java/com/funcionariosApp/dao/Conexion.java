/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.funcionariosApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Conexion {  
    private static Connection conectar;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private static Conexion conexion;
    private static final String url = "jdbc:mysql://localhost:3306/funcionarios";
    private static final String usuario = "root";
    private static final String contrasenia = "";
    
    public Connection conectarBaseDeDatos() {
        try {
            // Registro al modelo
           Class.forName(this.driver);
           conectar = DriverManager.getConnection(this.url, this.usuario, this.contrasenia);
           return conectar;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            return conectar;
        }
    }
    
    public void desconectarBaseDatos() {
        try {
            conectar.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static Conexion getConexion() {
        if(conexion == null) {
            conexion = new Conexion();
        }
        
        return conexion;
    }
}

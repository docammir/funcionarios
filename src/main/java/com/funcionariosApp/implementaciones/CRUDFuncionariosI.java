/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.funcionariosApp.implementaciones;

import com.funcionariosApp.modelos.Funcionario;
import java.util.List;

/**
 *
 * @author user
 */
public interface CRUDFuncionariosI {
    public void crearFuncionario(Funcionario funcionario);
    public void actualizarFuncionario(Funcionario funcionario, String numIden);
    public void eliminarFuncionario(String numIden);
    public List<Object[]> mostrarFuncionarios();
    public Object[] buscarFuncionario(String numIden); 
    
}

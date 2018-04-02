/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduardo.dao;

import com.eduardo.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laloe
 */
public class PersonaDAO extends DAO {

    public void registrar(Persona p) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO persona (nombre, sexo) values(?,?)");
            st.setString(1, p.getNombre());
            st.setString(2, p.getSexo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    public List<Persona> listar() throws Exception{
        List<Persona> lista;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareCall("SELECT codigo, nombre, sexo FROM persona");
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()){
                Persona per = new Persona();
                per.setCodigo(rs.getInt("codigo"));
                per.setNombre(rs.getString("nombre"));
                per.setSexo(rs.getString("sexo"));
                lista.add(per);
            }
        } catch(Exception e){
            throw e;
        } finally {
            this.Cerrar();
        }
        
        return lista;
    }
    
    public Persona leerID(Persona per) throws Exception{
        Persona persona = null;
        ResultSet rs;
        
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, sexo FROM persona WHERE codigo = ?");
            st.setInt(1, per.getCodigo());
            rs = st.executeQuery();
            while (rs.next()){
                persona = new Persona();
                persona.setCodigo(rs.getInt("codigo"));
                persona.setNombre(rs.getString("nombre"));
                persona.setSexo(rs.getString("sexo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        
        return persona;
    }
    
    public void modificar(Persona p) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE persona SET nombre = ?, sexo = ? WHERE codigo = ?");
            st.setString(1, p.getNombre());
            st.setString(2, p.getSexo());
            st.setInt(3, p.getCodigo());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}

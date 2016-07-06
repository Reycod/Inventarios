/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import mapeos.Producto;
import mapeos.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class UsuarioDao 
{
    Session sesion;
    Transaction tx;
    //Metodo que inicia la sesion
    
    public void iniciarOperacion() 
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    
    public void manejaexception(HibernateException he) 
    {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos");
    }


    public boolean registarUsuario(Usuario user) throws Exception 
    {
        iniciarOperacion();
        sesion.save(user);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public boolean actualizarUsuario(Usuario user) throws Exception 
    {
        iniciarOperacion();
        sesion.update(user);
        tx.commit();
        sesion.close();
        return true;
    }
    
    public boolean eliminarUsuario(Usuario user) throws Exception 
    {
        iniciarOperacion();
        sesion.delete(user);
        tx.commit();
        sesion.close();
        return true;
    }

    public List<Usuario> listarUsuarios() throws Exception 
    {
        iniciarOperacion();
        Query query = sesion.createQuery("From Usuario");
        List<Usuario> lista = query.list();
        sesion.close();
        return lista;
    }
    
    //Metodo que realiza la busqueda de un usuario por el id
    public Usuario buscarUsuario(int id) throws Exception 
    {
        Usuario user = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Usuario Where idusuario=?");
        query.setInteger(0, id);
        user = (Usuario) query.uniqueResult();
        sesion.close();
        return user;
    }
}

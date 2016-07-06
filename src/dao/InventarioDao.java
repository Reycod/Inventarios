/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import mapeos.Fifo;
import mapeos.Inventario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Reynaldo
 */
public class InventarioDao {

    Session sesion;
    Transaction tx;

    //Metodo que inicia la sesion 
    public void iniciarOperacion() {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void manejaexception(HibernateException he) {
        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos");
    }

    public boolean registarInventario(Inventario inv) throws Exception {
        iniciarOperacion();
        sesion.save(inv);
        tx.commit();
        sesion.close();
        return true;
    }

    public boolean actualizarInventario(Inventario inv) throws Exception {
        iniciarOperacion();
        sesion.update(inv);
        tx.commit();
        sesion.close();
        return true;
    }

    //Funcion que verifica si un producto ya existe en almacen
    public Inventario existeProducto(int idAlmacen, int idProducto) {
        Inventario inv = null;
        iniciarOperacion();
        Query query = sesion.createQuery("From Inventario Where almacen_idalmacen=? and producto_idproducto=?");
        query.setInteger(0, idAlmacen);
        query.setInteger(1, idProducto);
        inv = (Inventario) query.uniqueResult();
        sesion.close();
        return inv;
    }

    //Metodo que realiza el listado del kardex de inventario por almacen
    public List<Object[]> kardexInventarioAlmacen(int idAlmacen) throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT  p.codigoproducto,p.nombre,iv.stock,p.stockMinimo,p.stockMaximo,p.idproducto,p.precio FROM Producto p, Inventario iv WHERE iv.producto_idproducto=idproducto and iv.almacen_idalmacen=?");
        query.setInteger(0, idAlmacen);
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }

    //Metodo que realiza el listado del kardex de inventario por almacen
    public List<Object[]> KardexProducto(int idAlmacen, String nomProd) throws Exception {
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT  p.codigoproducto,p.nombre,iv.stock,p.stockMinimo,p.stockMaximo FROM Producto p, Inventario iv WHERE iv.producto_idproducto=p.idproducto and iv.almacen_idalmacen=? and p.nombre=?");
        query.setInteger(0, idAlmacen);
        query.setString(1, nomProd);
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }

    //Metodo que recuepra el ultimo registro del kardex FIFO
    public List<Object[]> ultimoRegistroFIFO(int idInv) {
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT f.cant_saldo,f.precio_saldo,f.p_total_saldo FROM Fifo AS f WHERE f.fecha=(SELECT  MAX(fecha) FROM Fifo WHERE inventario.idinventario=?)");
        query.setInteger(0, idInv);
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }

    public int  buscarTipoInventario(int idInv) 
    {
        int inv = 0;
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT tipoInventario  FROM Inventario   WHERE producto_idproducto=?");
        query.setInteger(0, idInv);
        inv = (int) query.uniqueResult();
        sesion.close();
        return inv;
    }
    
    public int  buscarIdInventario(int idProd) 
    {
        int inv = 0;
        iniciarOperacion();
        Query query = sesion.createQuery("SELECT idinventario  FROM Inventario   WHERE producto_idproducto=?");
        query.setInteger(0, idProd);
        inv = (int) query.uniqueResult();
        sesion.close();
        return inv;
    }
    
    //Metodo que recuepra el ultimo registro del kardex FIFO
    public List<Object[]> KardexPespsCompras(int idInv, int idAlmacen) 
    {
        
        iniciarOperacion();
        
        Query query = sesion.createQuery("Select  distinct f.fecha,f.documento,f.ndocumento,f.detalle,f.cant_entrada,f.precio_entrada,f.p_total_entrada,f.cant_saldo,f.precio_saldo,f.p_total_saldo  FROM Fifo as f, Inventario as i \n" +
"WHERE  f.inventario.idinventario=? AND  f.inventario.almacen_idalmacen=?  AND f.fecha >= ( Select max (fecha) from Fifo Where documento='INVENTARIO INICIAL' and inventario.idinventario=?)");
        query.setInteger(0, idInv);
        query.setInteger(1, idAlmacen);
        query.setInteger(2, idInv);
        List<Object[]> lista = query.list();
        sesion.close();
        return lista;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeos;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 *
 * @author Reynaldo
 */
public class ListaPrecios implements Serializable
{
    private int idlista_precios;
    private String nombre;
    private Date fecha;
    private String codigo;
    private String estado;
    private int idAlmacen;//id del almacen al que pertenece esta lista de precios
    private Set<Productos_lista_precios> listaProductos;

    public ListaPrecios() {
    }

    public int getIdlista_precios() {
        return idlista_precios;
    }

    public void setIdlista_precios(int idlista_precios) {
        this.idlista_precios = idlista_precios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Set<Productos_lista_precios> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(Set<Productos_lista_precios> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void setFecha(String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeos;

import java.io.Serializable;

/**
 *
 * @author Reynaldo
 */
public class Productos_lista_precios implements Serializable{
    
    private int idproductoslista;
    private int idProducto;
    private String precio;
    private ListaPrecios listaPrecios;

    public Productos_lista_precios() {
    }

    public int getIdproductoslista() {
        return idproductoslista;
    }

    public void setIdproductoslista(int idproductoslista) {
        this.idproductoslista = idproductoslista;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public ListaPrecios getListaPrecios() {
        return listaPrecios;
    }

    public void setListaPrecios(ListaPrecios listaPrecios) {
        this.listaPrecios = listaPrecios;
    }
    
    
}

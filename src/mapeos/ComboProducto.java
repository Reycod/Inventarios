/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeos;

import java.util.Set;

/**
 *
 * @author Reynaldo
 */
public class ComboProducto 
{

    private int idcomboProducto;
    private String codigo;
    private String nombre;
    private String precioMercado;
    private String precioCliente;
    private String precioMayorista;
    private String estado;
    private Set<ItemsCombo> itemsCombo;

    public ComboProducto() {
    }

    public int getIdcomboProducto() {
        return idcomboProducto;
    }

    public void setIdcomboProducto(int idcomboProducto) {
        this.idcomboProducto = idcomboProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecioMercado() {
        return precioMercado;
    }

    public void setPrecioMercado(String precioMercado) {
        this.precioMercado = precioMercado;
    }

    public String getPrecioCliente() {
        return precioCliente;
    }

    public void setPrecioCliente(String precioCliente) {
        this.precioCliente = precioCliente;
    }

    public String getPrecioMayorista() {
        return precioMayorista;
    }

    public void setPrecioMayorista(String precioMayorista) {
        this.precioMayorista = precioMayorista;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<ItemsCombo> getItemsCombo() {
        return itemsCombo;
    }

    public void setItemsCombo(Set<ItemsCombo> itemsCombo) {
        this.itemsCombo = itemsCombo;
    }
}

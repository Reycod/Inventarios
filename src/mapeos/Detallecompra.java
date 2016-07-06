/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeos;

/**
 *
 * @author Reynaldo
 */
public class Detallecompra 
{

    private int iddetallecompra;
    private String cantidad;
    private Compra compra;
    private Integer produto_idproducto;
    private String estado;
    private String precioCompra;

    public Detallecompra() 
    {
        
    }

    public Detallecompra(String cantidad, Integer idproducto,Compra compra,String estado) {
        this.cantidad = cantidad;
        this.compra = compra;
        this.produto_idproducto=idproducto;
        this.estado=estado;
        
    }

    public int getIddetallecompra() {
        return iddetallecompra;
    }

    public void setIddetallecompra(int iddetallecompra) {
        this.iddetallecompra = iddetallecompra;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Integer getProduto_idproducto() {
        return produto_idproducto;
    }

    public void setProduto_idproducto(Integer produto_idproducto) {
        this.produto_idproducto = produto_idproducto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(String precioCompra) {
        this.precioCompra = precioCompra;
    }
}

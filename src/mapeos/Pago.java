/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeos;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Reynaldo
 */
public class Pago 
{
   private int id;
   private String tipo;
   private String codigo;
   private Set compras = new HashSet(0);

    public Pago() {
    }

    public Pago(int id, String tipo, String codigo) {
        this.id = id;
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }  

    public Set getCompras() {
        return compras;
    }

    public void setCompras(Set compras) {
        this.compras = compras;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}

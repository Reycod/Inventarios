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
public class UnidadMedida {

    private int idunidadmedida;
    private String nombre;
    private String abreviacion;
    
    private Set productos = new HashSet(0);

    public UnidadMedida() {
    }

    public UnidadMedida(int idunidadmedida, String nombre, String abreviacion) {
        this.idunidadmedida = idunidadmedida;
        this.nombre = nombre;
        this.abreviacion = abreviacion;
        
    }

    
    public UnidadMedida(String nombre, String descripcion) {
        this.nombre = nombre;
        this.abreviacion = descripcion;
    }

    public int getIdunidadmedida() {
        return idunidadmedida;
    }

    public void setIdunidadmedida(int idunidadmedida) {
        this.idunidadmedida = idunidadmedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public Set getProductos() {
        return productos;
    }

    public void setProductos(Set productos) {
        this.productos = productos;
    }

}

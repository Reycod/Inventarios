package mapeos;
// Generated 10-02-2016 05:32:08 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Almacen generated by hbm2java
 */
public class Almacen  implements java.io.Serializable {


     private Integer idalmacen;
     //private Encargado encargado;
     private String nombre;
     private String direccion;
     private String telefono;
     private String codigoAlamacen;
     private Set inventarios = new HashSet(0);
     
     private Set encargado = new HashSet(0);

    public Almacen() 
    {
    }

    public Almacen(Integer idalmacen, String nombre, String direccion, String telefono, String codigoAlamacen) {
        this.idalmacen = idalmacen;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoAlamacen = codigoAlamacen;
    }


   
    public Almacen(String nombre, String direccion, String telefono, String codigoAlamacen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoAlamacen = codigoAlamacen;
    }
	
    public Almacen(Set encargado) {
        this.encargado = encargado;
    }
    public Almacen(Set encargado, String nombre, String direccion, String telefono, String codigoAlamacen, Set inventarios) {
       this.encargado = encargado;
       this.nombre = nombre;
       this.direccion = direccion;
       this.telefono = telefono;
       this.codigoAlamacen = codigoAlamacen;
       this.inventarios = inventarios;
    }
   
    public Integer getIdalmacen() {
        return this.idalmacen;
    }
    
    public void setIdalmacen(Integer idalmacen) {
        this.idalmacen = idalmacen;
    }
    public Set getEncargado() {
        return this.encargado;
    }
    
    public void setEncargado(Set encargado) {
        this.encargado = encargado;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCodigoAlamacen() {
        return this.codigoAlamacen;
    }
    
    public void setCodigoAlamacen(String codigoAlamacen) {
        this.codigoAlamacen = codigoAlamacen;
    }
    public Set getInventarios() {
        return this.inventarios;
    }
    
    public void setInventarios(Set inventarios) {
        this.inventarios = inventarios;
    }




}



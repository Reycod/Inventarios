package mapeos;
// Generated 10-02-2016 05:32:08 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Encargado generated by hbm2java
 */
public class Encargado  implements java.io.Serializable 
{
     private Integer idencargado;
     private Usuario usuario;
     private Almacen almacens = new Almacen();

    public Encargado() 
    {
        
    }
	
    public Encargado(Usuario usuario)
    {
        this.usuario = usuario;
    }
    public Encargado(Usuario usuario, Almacen almacens) {
       this.usuario = usuario;
       this.almacens = almacens;
    }
   
    public Integer getIdencargado() {
        return this.idencargado;
    }
    
    public void setIdencargado(Integer idencargado) {
        this.idencargado = idencargado;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Almacen getAlmacens() {
        return this.almacens;
    }
    
    public void setAlmacens(Almacen almacens) {
        this.almacens = almacens;
    }
}



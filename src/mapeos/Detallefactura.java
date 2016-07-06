package mapeos;
// Generated 10-02-2016 05:32:08 PM by Hibernate Tools 3.6.0



/**
 * Detallefactura generated by hbm2java
 */
public class Detallefactura  implements java.io.Serializable {


     private Integer iddetallefactura;
     private Producto producto;
     private Factura factura;
     private String descripcion;
     private String cantidad;
     private String preciounitario;
     private String preciototal;

    public Detallefactura() {
    }

	
    public Detallefactura(Producto producto, Factura factura) {
        this.producto = producto;
        this.factura = factura;
    }
    public Detallefactura(Producto producto, Factura factura, String descripcion, String cantidad, String preciounitario, String preciototal) {
       this.producto = producto;
       this.factura = factura;
       this.descripcion = descripcion;
       this.cantidad = cantidad;
       this.preciounitario = preciounitario;
       this.preciototal = preciototal;
    }
   
    public Integer getIddetallefactura() {
        return this.iddetallefactura;
    }
    
    public void setIddetallefactura(Integer iddetallefactura) {
        this.iddetallefactura = iddetallefactura;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Factura getFactura() {
        return this.factura;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    public String getPreciounitario() {
        return this.preciounitario;
    }
    
    public void setPreciounitario(String preciounitario) {
        this.preciounitario = preciounitario;
    }
    public String getPreciototal() {
        return this.preciototal;
    }
    
    public void setPreciototal(String preciototal) {
        this.preciototal = preciototal;
    }




}


package mapeos;
// Generated 10-02-2016 05:32:08 PM by Hibernate Tools 3.6.0




import java.util.Date;
import java.util.Set;


/**
 * Compra generated by hbm2java
 */
public class Compra implements java.io.Serializable {

    private Integer idcompra;
    private Proveedor proveedor;
    private Date fecha;
    private String fechaemtrega;
    private String preciototal;
    private String codigo;
    private String estado;
    private String ncotizacion;
    private String responsable;
    private Set<Detallecompra> detalleCompra;
    private Pago pago;
    private String responsableRecepcion;
    private String fechaRecepcion;
    private String tipoDocumento;
    private int numeroDocumento;
    private String obsRecepcion;
    
    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getResponsableRecepcion() {
        return responsableRecepcion;
    }

    public void setResponsableRecepcion(String responsableRecepcion) {
        this.responsableRecepcion = responsableRecepcion;
    }


    public Compra() {
    }

    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFechaemtrega() {
        return fechaemtrega;
    }

    public void setFechaemtrega(String fechaemtrega) {
        this.fechaemtrega = fechaemtrega;
    }

    public String getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(String preciototal) {
        this.preciototal = preciototal;
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

    public String getNcotizacion() {
        return ncotizacion;
    }

    public void setNcotizacion(String ncotizacion) {
        this.ncotizacion = ncotizacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Set<Detallecompra> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(Set<Detallecompra> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getObsRecepcion() {
        return obsRecepcion;
    }

    public void setObsRecepcion(String obsRecepcion) {
        this.obsRecepcion = obsRecepcion;
    }
}

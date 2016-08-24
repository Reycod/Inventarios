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
public class ItemsCombo 
{
    private int iditemsCombo;
    private int idProducto;
    private int cantidad;
    private ComboProducto combo;

    public ItemsCombo() {
    }

    public int getIditemsCombo() {
        return iditemsCombo;
    }

    public void setIditemsCombo(int iditemsCombo) {
        this.iditemsCombo = iditemsCombo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ComboProducto getCombo() {
        return combo;
    }

    public void setCombo(ComboProducto combo) {
        this.combo = combo;
    }
    
}

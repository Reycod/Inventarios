/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Reynaldo
 */
public class CodigoControlOC {

    //Metodo que genera el numero de control para la OC
    public static String generarCodigoControl(String prefijo) 
    {
        Calendar calendario = Calendar.getInstance();
        calendario = new GregorianCalendar();

        
        String ano = String.valueOf(calendario.get(Calendar.YEAR));
        String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
        String dia = String.valueOf(calendario.get(Calendar.DAY_OF_MONTH));
        String hora = String.valueOf(calendario.get(Calendar.HOUR));
        String minuto = String.valueOf(calendario.get(Calendar.MINUTE));
        String segundo = String.valueOf(calendario.get(Calendar.SECOND));

        return prefijo + ano+mes+dia+hora+minuto+segundo; 

    }

//    public static void main(String args[]) 
//    {
//        //+ mes + dia + hora + minuto + segundo;
//        
//        JOptionPane.showMessageDialog(null, ""+generarCodigoControl("OC-"));
//    }
}

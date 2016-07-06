/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sysintory_v0.pkg1;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import view.vtnLogin;


/**
 *
 * @author Reynaldo
 */
public class SysIntory_V01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try 
        {
            //Iniciando la libreria JTatoo para cambiar el estilo 
          UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        
          
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Error"+e.getMessage());
        }
        vtnLogin logueo = new vtnLogin();
        logueo.setVisible(true);
    }
    
}

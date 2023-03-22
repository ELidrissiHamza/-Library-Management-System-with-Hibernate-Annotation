/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.fstm.ilisi.biblio.controler;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author S USER
 */
public class ControllerUpdates {
    public static void updateTables(JTable tabLivre ,JTable tabAds,JTable tabEmps){
        ControllerLivre.getlivres(tabLivre);
        ControllerAdherant.getAdherants(tabAds);
        ControllerEmprunt.getEmprunts(tabEmps);
    }
     public static void setItems(JComboBox j1, JComboBox j2) {
              ControllerAdherant.additemsAdherents(j1);
                ControllerLivre.additemsLivre(j2);    }
    
}

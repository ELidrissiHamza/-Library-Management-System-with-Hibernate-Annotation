/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.fstm.ilisi.biblio.controler;

import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import ma.fstm.ilisi.biblio.model.bo.Adherant;
import ma.fstm.ilisi.biblio.model.bo.Livre;
import ma.fstm.ilisi.biblio.model.dao.DAOAdherant;
import ma.fstm.ilisi.biblio.model.dao.DAOLivre;

/**
 *
 * @author S USER
 */
public class ControllerAdherant {
    public static void ajouterAdherent(String nom, String prenom, String CIN,JFrame f) {
         Adherant   ads=new Adherant(nom,prenom,CIN);
         DAOAdherant daoA =new DAOAdherant();
         String msg=daoA.create(ads)?"Adherant bien ajoute":"probleme d'ajout";
         JOptionPane.showMessageDialog(f, msg);

    }
      public static void modiffierAdherant(  String nom,String prenom,String cin,JFrame f){
        Adherant ads=new Adherant(nom,prenom,cin);
        DAOAdherant dao=new DAOAdherant( );
        String msg= dao.update(ads)?"Adherent modifie":"probleme de modifications";
        JOptionPane.showMessageDialog(f, msg);
    }
    public static void supprimerAdherant( String nom,String prenom,String cin, JFrame frame){
        Adherant ad=new Adherant(nom,prenom,cin);
        DAOAdherant dao=new DAOAdherant( );
       int res = JOptionPane.showConfirmDialog(frame,"Etes-vous sur?","Voullez vous Vraiment supprimer l'adherent",JOptionPane.WARNING_MESSAGE);
       if(res == JOptionPane.YES_OPTION)
        {
            String msg=dao.delete(ad)?"Adherent supprimee":"probleme de suppression";
            JOptionPane.showMessageDialog(frame, msg);    
        }
    }
    public static void getAdherants(JTable tab)
    {
           DAOAdherant dao = new DAOAdherant();
           Vector<Adherant> listAdherant    =( Vector<Adherant>)dao.retreive();
           Vector < Vector<Adherant> > matrice = new  Vector <>();
           
           Vector<String>  header = new  Vector<String>();
           header.add("ID");
           header.add("Nom");
           header.add("Prem");
           
            for(Adherant l :listAdherant )
           {
                Vector v = new Vector();
                v.add(l.getCin());

                v.add(l.getNom());
                v.add(l.getPrenom());
                matrice.add(v);
           
           }
        
           tab.setModel(new javax.swing.table.DefaultTableModel(matrice,header));
    }
    public static void setFormInputs(String CIN,JTextField field1,JTextField field2,JTextField field3){
          DAOAdherant dao = new DAOAdherant();
         
    
           Adherant   ads  =dao.findByCin(CIN);
           if(ads!=null){
               field1.setText(ads.getCin());
               field2.setText(ads.getNom());
               field3.setText(ads.getPrenom());
           }
    }
    public static void additemsAdherents(JComboBox jj)
    {
        DAOAdherant dao=new DAOAdherant();
        
        Vector<Adherant> adherents=(Vector<Adherant>)dao.retreive();
        
        for(Adherant l:adherents)
        {System.out.println("******"+l.getCin()+":"+l.getNom());
            jj.addItem(l.getCin());
        }
    }
}


package ma.fstm.ilisi.biblio.controler;

import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import ma.fstm.ilisi.biblio.model.bo.Adherant;
import ma.fstm.ilisi.biblio.model.bo.Exemplaire;
import ma.fstm.ilisi.biblio.model.bo.Livre;
import ma.fstm.ilisi.biblio.model.dao.DAOAdherant;
import ma.fstm.ilisi.biblio.model.dao.DAOExemplaire;
import ma.fstm.ilisi.biblio.model.dao.DAOLivre;

public class ControllerLivre {

    public static void ajouterLivre(String iddep, String text,Long nbr,JFrame f) {
         Livre   L=new Livre(iddep,text);
         DAOLivre daoL =new DAOLivre();
         String msg=daoL.create(L)?"Livre bien ajoute":"probleme d'ajout";
         DAOExemplaire daoE=new DAOExemplaire();
         System.out.println("*******"+nbr);
         for(int i=0;i<nbr;i++) daoE.create(new Exemplaire(i, L));
         
         JOptionPane.showMessageDialog(f, msg);

    }
     public static void modiffierLivre(String is, String tit,Long nbr,JFrame f){
        Livre liv=new Livre(is,tit);
        DAOLivre daoL=new DAOLivre( );
        DAOExemplaire daoE=new DAOExemplaire();
        Long countExp=daoE.countIsbn(liv.getIsbn());
        Long change=nbr - countExp;
        Long explaireEmprut=daoE.countExpEmprunt(liv.getIsbn());
       
        String msg= daoL.update(liv)?"Livre modifie":"probleme de modifications";
        
        if(change>=0) {
            for(int i=0;i<change;i++) daoE.create(new Exemplaire( i,liv));
        }else if((explaireEmprut-change)<=countExp){
            int i=-change.intValue();
            Vector<Exemplaire> empruLivs=daoE.retreiveExpEmprunt(is);
            for(Exemplaire e: daoE.retreiveByIsbn(is)){
                if(i==0) break;
                if(!empruLivs.contains(e)){
                    daoE.delete(e);
                    i--;
                }
            }
        }else {// 5 exp  3 emprunt retreiveExpEmprunt
            msg="impossible de diminuer le nombre des exemplaire tanqu'il son emprute";

        }
        JOptionPane.showMessageDialog(f, msg);
        
    }
    public static void supprimerLivre(String is, String tit, JFrame frame){
        Livre liv=new Livre(is,tit);
        DAOLivre daoL=new DAOLivre( );
       int res = JOptionPane.showConfirmDialog(frame,"Etes-vous sur?","Voullez vous Vraiment supprimer le livre",JOptionPane.WARNING_MESSAGE);
       if(res == JOptionPane.YES_OPTION)
        {
            String msg=daoL.delete(liv)?"Livre supprimee":"probleme de suppression";
            JOptionPane.showMessageDialog(frame, msg);    
        }
    }
    public static void getlivres(JTable tab)
    {
            DAOExemplaire daoE=new DAOExemplaire();
           DAOLivre dao = new DAOLivre();
           Vector<Livre> listlivre    =( Vector<Livre>)dao.retreive();
           Vector < Vector<Livre> > matrice = new  Vector <>();
           
           Vector<String>  header = new  Vector<String>();
           header.add("ISBN");
           header.add("Titre");
           header.add("nbr examplaire"); 
           header.add("nbr emprunte"); 
            for(Livre l :listlivre )
           {
                Vector v = new Vector();
                v.add(l.getIsbn());
                v.add(l.getTitre());
                v.add(String.valueOf(daoE.countIsbn(l.getIsbn())));
                v.add(String.valueOf(daoE.countExpEmprunt(l.getIsbn())));

                matrice.add(v);
           
           }
        
           tab.setModel(new javax.swing.table.DefaultTableModel(matrice,header));
    }
    public static void setFormInputs(String isbn,JTextField field1,JTextField field2,JTextField field3){
          DAOLivre dao = new DAOLivre();
          DAOExemplaire daoE =new DAOExemplaire();
    
           Livre   livre  =dao.findByIsbn(isbn);
           if(livre!=null){
               field2.setText(livre.getIsbn());
               field1.setText(livre.getTitre());//
               field3.setText(String.valueOf(daoE.countIsbn(livre.getIsbn())));
           }
    }
    
     public static void addListLivre(JComboBox combo){
                  DAOLivre dao = new DAOLivre();
                  combo.removeAllItems();
                  for(Livre liv:dao.retreive()){                            
                      combo.addItem(liv.getIsbn());
                  }

    }
      public static void additemsLivre(JComboBox j)
    {
        DAOLivre dao=new DAOLivre();
        Vector<Livre> livres=(Vector<Livre>) dao.retreive();
        for(Livre l:livres)
        {System.out.println("******"+l.getIsbn());
            j.addItem(l.getIsbn());
        }
        
    }
}

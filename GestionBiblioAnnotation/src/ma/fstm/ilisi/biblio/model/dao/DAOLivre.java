package ma.fstm.ilisi.biblio.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import ma.fstm.ilisi.biblio.model.bo.Exemplaire;
import ma.fstm.ilisi.biblio.model.bo.Livre;
import ma.fstm.ilisi.biblio.model.idao.IDAOLivre;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 *
 * @author S USER
 */
public class DAOLivre implements IDAOLivre {

    @Override
    public boolean create(Livre D) {
        Transaction tx = null;
        try {
            Session session = NewHibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(D);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            tx.rollback();
            return false;

        }

    }
    @Override
     public Vector<Livre> retreive() {
        
        List<Livre> livre = new  Vector<Livre>();
        Transaction tx=null;
        try{
            Session session=NewHibernateUtil.getsessionFactory().getCurrentSession();
           tx=session.beginTransaction();       
            Query query = session.createQuery("from Livre");
            for(final Object o : query.list()) livre.add((Livre)o);
            for(Livre l:livre){
                for(Object o:l.getExemplaires()){
                    Exemplaire e= (Exemplaire )o;
                System.out.println(e.getIdexp());
                }
     }
            tx.commit();
        }catch(HibernateException e){
            tx.rollback();
            System.out.println("error loading: "+e);
        }  
        
        return (Vector<Livre>)livre;  
               
    }

    @Override
    public boolean update(Livre L) {
         Transaction tx = null;
         String isbn=L.getIsbn(),titre=L.getTitre();
        try {
            Session session = NewHibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            L=(Livre) session.load(Livre.class, isbn);
            L.setTitre(titre);
          //  session.saveOrUpdate(L);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            tx.rollback();
            return false;

        }
    }
@Override
    public boolean delete(Livre L) {
         Transaction tx = null;
        try {
            Session session = NewHibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.delete(L);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            tx.rollback();
            return false;

        }
    }

    public Livre findByIsbn(String isbn){
        Livre livre =null;
        Transaction tx = null;
         try
         { 
            Session session = NewHibernateUtil.getsessionFactory().getCurrentSession();
            tx = session.beginTransaction();
         
            livre = (Livre) session.get(Livre.class, isbn);
            tx.commit();
            return livre;
         }
         catch(HibernateException e)
         {
            tx.rollback();
            return null;
         }
    }

}

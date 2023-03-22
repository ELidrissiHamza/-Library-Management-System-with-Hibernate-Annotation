/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ma.fstm.ilisi.biblio.model.idao;

import java.util.Collection;
import ma.fstm.ilisi.biblio.model.bo.Livre;

/**
 *
 * @author ilisi
 */
public interface IDAOLivre {
    boolean create(Livre L);
    Collection<Livre> retreive();
    boolean update (Livre L);
    boolean delete(Livre L);
}

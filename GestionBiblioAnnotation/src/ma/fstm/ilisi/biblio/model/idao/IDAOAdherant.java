/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.fstm.ilisi.biblio.model.idao;

import java.util.Collection;
import ma.fstm.ilisi.biblio.model.bo.Adherant;

/**
 *
 * @author S USER
 */
public interface IDAOAdherant {
     boolean create(Adherant L);
    Collection<Adherant> retreive();
    boolean update (Adherant L);
    boolean delete(Adherant L);
}

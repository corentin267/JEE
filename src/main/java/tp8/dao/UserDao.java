package tp8.dao;

import tp8.entity.Driver;
import tp8.entity.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    public void create(User user) throws Exception {
        try {
            User userFound = getForUsername(user.getUsername());
            if (userFound != null) {
                throw new Exception("Username used");
            }
        } catch (NoResultException e) {
            try {
                userTransaction.begin();
                entityManager.persist(user);
                userTransaction.commit();
            } catch (Exception exception) {
                Logger.getGlobal().log(Level.SEVERE, "JPA error" + exception.getMessage());
                throw new Exception("Error during creation");
            }
        }
    }

    public User getForUsername(String username) {
        return entityManager.createQuery(
                "select user from User user where user.username = :valeur", User.class)
                .setParameter("valeur", username)
                .getSingleResult();
    }
}

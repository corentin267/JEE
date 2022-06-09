package tp8.dao;

import tp8.entity.User;
import tp8.entity.UserToken;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.UUID;

public class UserTokenDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    public String createToken(String username) throws Exception {
        try {
            entityManager.createQuery(
                    "select userToken from UserToken userToken where userToken.username = :valeur", UserToken.class)
                    .setParameter("valeur", username)
                    .getSingleResult();
            throw new Exception("User have already a token");
        } catch (NoResultException e) {
            var userToken = new UserToken();
            userToken.setUsername(username);
            UUID uuid = UUID.randomUUID();
            userToken.setToken(uuid.toString());

            userTransaction.begin();
            entityManager.persist(userToken);
            userTransaction.commit();
            return uuid.toString();
        }
    }

    public UserToken getForToken(String token) {
        return entityManager.createQuery(
                "select userToken from UserToken userToken where userToken.token = :valeur", UserToken.class)
                .setParameter("valeur", token)
                .getSingleResult();
    }

    public void delete(UserToken userToken) throws Exception {
        userTransaction.begin();
        entityManager.remove(entityManager.contains(userToken) ? userToken : entityManager.merge(userToken));
        userTransaction.commit();
    }
}

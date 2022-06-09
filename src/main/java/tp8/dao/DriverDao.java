package tp8.dao;

import tp8.entity.Driver;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    public List<Driver> getAll(){
        return entityManager.createQuery("select dr from Driver dr", Driver.class).getResultList();
    }

    public void create(Driver driver) throws Exception{
        try{
            userTransaction.begin();
            entityManager.persist(driver);
            userTransaction.commit();
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            throw new Exception();
        }
    }

    public Driver get(Long id) throws Exception {
        var data = entityManager.find(Driver.class, id);
        if (data == null) {
            throw new Exception();
        }
        return data;
    }

    public void update(Driver driver) throws Exception {
        try {
            userTransaction.begin();
            entityManager.merge(driver);
            userTransaction.commit();
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            throw new Exception();
        }
    }

    public void delete(Long id) throws Exception {
        try {
            var driver = this.get(id);
            if (driver == null) {
                throw new Exception();
            }
            userTransaction.begin();
            entityManager.remove(entityManager.contains(driver) ? driver : entityManager.merge(driver));
            userTransaction.commit();
        }catch (Exception e){
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            throw new Exception();
        }
    }
}

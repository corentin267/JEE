package tp8.bean;

import tp8.dao.DriverDao;
import tp8.entity.Driver;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DriverBean {

    @Inject
    private DriverDao driverDao;

    public List<Driver> getAll(){
        return driverDao.getAll();
    }

    public void create(Driver driver) throws Exception {
        driverDao.create(driver);
    }

    public void update(Driver driver) throws Exception {
        driverDao.update(driver);
    }

    public void delete(Long id) throws Exception {
        driverDao.delete(id);
    }
}

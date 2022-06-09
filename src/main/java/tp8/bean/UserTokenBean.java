package tp8.bean;

import tp8.dao.UserDao;
import tp8.dao.UserTokenDao;
import tp8.entity.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserTokenBean {

    @Inject
    UserTokenDao userTokenDao;

    public void getForToken(String token) {
        userTokenDao.getForToken(token);
    }
}

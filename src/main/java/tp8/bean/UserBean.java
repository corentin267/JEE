package tp8.bean;

import tp8.dao.UserDao;
import tp8.dao.UserTokenDao;
import tp8.entity.User;
import tp8.entity.UserToken;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserBean {

    @Inject
    private UserDao userDao;

    @Inject
    UserTokenDao userTokenDao;

    public void create(User user) throws Exception {
        user.setPassword(hashPassword(user.getPassword()));
        userDao.create(user);
    }

    public String login(User user) throws Exception {
        user.setPassword(hashPassword(user.getPassword()));
        var userFound = userDao.getForUsername(user.getUsername());

        if (!user.getPassword().equals(userFound.getPassword())) {
            throw new Exception("Bad Password");
        }

        return userTokenDao.createToken(user.getUsername());
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }

    public void logout(String token) throws Exception {
        UserToken result = userTokenDao.getForToken(token);
        userTokenDao.delete(result);
    }
}
